package com.override.myhomeserver.service;

import com.override.myhomeserver.dto.MeasureDTO;
import com.override.myhomeserver.enums.Period;
import com.override.myhomeserver.mapper.MeasureMapper;
import com.override.myhomeserver.model.Measure;
import com.override.myhomeserver.model.Sensor;
import com.override.myhomeserver.repository.MeasureRepository;
import com.override.myhomeserver.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.ZoneOffset.UTC;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class MeasureService {

    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private MeasureMapper measureMapper;

    public void putNewMeasure(MeasureDTO measureDTO) {
        measureRepository.save(
                Measure.builder()
                        .uuid(UUID.randomUUID())
                        .value(measureDTO.getValue())
                        .dateTime(LocalDateTime.now())
                        .sensor(sensorRepository.getById(measureDTO.getDeviceName()))
                        .build());
    }

    private List<MeasureDTO> getMeasuresForDevice(String deviceName) {
        Sensor sensor = sensorRepository.findById(deviceName).orElseThrow(IllegalArgumentException::new);
        return sensor.getMeasures()
                .stream()
                .map(measureMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<MeasureDTO> getMeasuresForDevice(String deviceName, Period period) {
        ChronoUnit chronoUnit = periodToChronoUnit(period);
        List<Measure> measures = sensorRepository.findById(deviceName).orElseThrow(IllegalArgumentException::new).getMeasures();
        ChronoUnit finalChronoUnit = chronoUnit;
        return measures
                .stream()
                //TODO вынести в sql
                .filter(measure -> measure.getDateTime().isAfter(LocalDateTime.now().minus(1, finalChronoUnit)))
                .map(measureMapper::toDTO)
                .collect(Collectors.toList());
    }


    public List<MeasureDTO> roundToMinutesAndFillWithZerosMissingData(List<MeasureDTO> measures, Period period) {
        //todo усреднение данных для одной и той же минуты

        List<MeasureDTO> truncatedMinutesUniqueMeasures = measures.stream()
                .sorted((x, x1) -> x.getDateTime().isAfter(x1.getDateTime()) ? 1 : -1)
                .map(measure -> measure.setDateTime(measure.getDateTime().truncatedTo(ChronoUnit.MINUTES)))
                .filter(distinctByKey(MeasureDTO::getDateTime))
                .collect(Collectors.toList());
        List<MeasureDTO> measuresWithFakeZeros = new ArrayList<>();

        if (truncatedMinutesUniqueMeasures.size() < 1) {
            return new ArrayList<>();
        }
        long firstMeasureForPeriod = toUnixTimeForBeginingOfPeriod(truncatedMinutesUniqueMeasures.get(0).getDateTime(), period);

        Iterator<MeasureDTO> realMeasuresIterator = truncatedMinutesUniqueMeasures.iterator();
        MeasureDTO measure = realMeasuresIterator.next();
        for (long i = firstMeasureForPeriod; ; i += 60) {
            LocalDateTime dateTime = LocalDateTime.ofEpochSecond(i, 0, UTC);
            if (dateTime.equals(measure.getDateTime())) {
                measuresWithFakeZeros.add(measure);
                if (realMeasuresIterator.hasNext()) {
                    measure = realMeasuresIterator.next();
                } else {
                    break;
                }
            } else {
                measuresWithFakeZeros.add(
                        MeasureDTO.builder()
                                .value(0d)
                                .dateTime(dateTime)
                                .build()
                );
            }
        }

        return measuresWithFakeZeros;
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    private ChronoUnit periodToChronoUnit(Period period) {
        switch (period) {
            case DAY:
                return DAYS;
            case WEEK:
                return ChronoUnit.WEEKS;
            case MONTH:
                return ChronoUnit.MONTHS;
            case ALL:
                return ChronoUnit.CENTURIES;
        }
        return ChronoUnit.CENTURIES;
    }

    private long toUnixTimeForBeginingOfPeriod(LocalDateTime localDateTime, Period period) {
        switch (period) {
            case DAY:
                return localDateTime.truncatedTo(DAYS).toEpochSecond(UTC);
            case WEEK:
                LocalDateTime truncatedToMonth = localDateTime.truncatedTo(DAYS).minusDays(7);
                return truncatedToMonth.toEpochSecond(UTC);
            case MONTH:
                truncatedToMonth = localDateTime.truncatedTo(DAYS).minusMonths(1);
                return truncatedToMonth.toEpochSecond(UTC);
            case ALL:
                //year
                truncatedToMonth = localDateTime.truncatedTo(DAYS).minusYears(1);
                return truncatedToMonth.toEpochSecond(UTC);
        }
        return localDateTime.truncatedTo(DAYS).toEpochSecond(UTC);
    }
}
