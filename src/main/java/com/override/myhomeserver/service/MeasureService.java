package com.override.myhomeserver.service;

import com.override.myhomeserver.dto.MeasureDTO;
import com.override.myhomeserver.enums.Period;
import com.override.myhomeserver.model.Measure;
import com.override.myhomeserver.model.Sensor;
import com.override.myhomeserver.repository.MeasureRepository;
import com.override.myhomeserver.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SuppressWarnings("ConstantConditions")
@Service
public class MeasureService {

    @Autowired
    private MeasureRepository measureRepository;

    @Autowired
    private SensorRepository sensorRepository;

    public void putNewMeasure(MeasureDTO measureDTO) {
        measureRepository.save(
                Measure.builder()
                        .uuid(UUID.randomUUID())
                        .value(measureDTO.getValue())
                        .dateTime(LocalDateTime.now())
                        .sensor(sensorRepository.getById(measureDTO.getDeviceName()))
                        .build());
    }

    private List<Measure> getMeasuresForDevice(String deviceName) {
        Sensor sensor = sensorRepository.findById(deviceName).orElseThrow(IllegalArgumentException::new);
        return sensor.getMeasures();
    }

    public List<Measure> getMeasuresForDevice(String deviceName, Period period) {
        List<Measure> measures = sensorRepository.findById(deviceName).orElseThrow(IllegalArgumentException::new).getMeasures();
        ChronoUnit chronoUnit = ChronoUnit.DAYS;
        //todo вынести
        switch (period) {
            case DAY:
                chronoUnit = ChronoUnit.DAYS;
                break;
            case WEEK:
                chronoUnit = ChronoUnit.WEEKS;
                break;
            case MONTH:
                chronoUnit = ChronoUnit.MONTHS;
                break;
            case ALL:
                return getMeasuresForDevice(deviceName);
        }
        ChronoUnit finalChronoUnit = chronoUnit;
        return measures
                .stream()
                //TODO вынести в sql
                .filter(measure -> measure.getDateTime().isAfter(LocalDateTime.now().plus(1, finalChronoUnit)))
                .collect(Collectors.toList());
    }

    @SuppressWarnings("ComparatorMethodParameterNotUsed")
    private List<Measure> roundToMinutesAndFillWithZerosMissingData(List<Measure> measures) {


        //todo усреднение данных для одной и той же минуты
        List<Measure> truncatedMinutesUniqueMeasures = measures.stream()
                .sorted((x, x1) -> x.getDateTime().isAfter(x1.getDateTime()) ? 1 : -1)
                .map(measure -> measure.setDateTime(measure.getDateTime().truncatedTo(ChronoUnit.MINUTES)))
                .filter(distinctByKey(Measure::getDateTime))
                .collect(Collectors.toList());
        List<Measure> measuresWithFakeZeros = new ArrayList<>();
       //TODO дополнить нулями для минут когда не было данных
        return null;
    }

    public static <T> Predicate<T> distinctByKey(
            Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
