package com.override.myhomeserver.service;

import com.override.myhomeserver.dto.MeasureDTO;
import com.override.myhomeserver.enums.Period;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
//TODO полечить тесты
@ExtendWith(MockitoExtension.class)
public class MeasureServiceTest {

    @InjectMocks
    private MeasureService measureService;

    @Test
    public void roundToMinutesAndFillWithZerosMissingDataTest() {
        List<MeasureDTO> testMeasures = new ArrayList<>();
        LocalDateTime firstDateTime = LocalDateTime.ofEpochSecond(518400, 0, ZoneOffset.UTC);

        testMeasures.add(new MeasureDTO("deviceName", 3d, firstDateTime));
        testMeasures.add(new MeasureDTO("deviceName", 3d, firstDateTime.plus(5, ChronoUnit.MINUTES)));
        List<MeasureDTO> resultWithZeros = measureService.roundToMinutesAndFillWithZerosMissingData(testMeasures, Period.DAY);

        Assertions.assertEquals(6, resultWithZeros.size());
    }

    @Test
    public void roundToMinutesAndFillWithZerosMissingDataTestWhenMoreZerosAtStartOfTheDay() {
        List<MeasureDTO> testMeasures = new ArrayList<>();
        LocalDateTime firstDateTime = LocalDateTime.ofEpochSecond(519400, 0, ZoneOffset.UTC);

        testMeasures.add(new MeasureDTO("deviceName", 3d, firstDateTime));
        testMeasures.add(new MeasureDTO("deviceName", 3d, firstDateTime.plus(5, ChronoUnit.MINUTES)));
        List<MeasureDTO> resultWithZeros = measureService.roundToMinutesAndFillWithZerosMissingData(testMeasures, Period.DAY);

        Assertions.assertEquals(22, resultWithZeros.size());
    }

    @Test
    public void roundToMinutesAndFillWithZerosMissingDataTestWhenEmptyMeasures() {
        List<MeasureDTO> testMeasures = new ArrayList<>();

        List<MeasureDTO> resultWithZeros = measureService.roundToMinutesAndFillWithZerosMissingData(testMeasures, Period.DAY);

        Assertions.assertEquals(0, resultWithZeros.size());
    }

}
