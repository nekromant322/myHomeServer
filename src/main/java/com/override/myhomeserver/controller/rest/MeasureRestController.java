package com.override.myhomeserver.controller.rest;

import com.override.myhomeserver.dto.MeasureDTO;
import com.override.myhomeserver.enums.Period;
import com.override.myhomeserver.model.Measure;
import com.override.myhomeserver.service.MeasureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/measure")
public class MeasureRestController {

    @Autowired
    private MeasureService measureService;

    @PutMapping
    public void newMeasure(@RequestBody MeasureDTO measure) {
        measureService.putNewMeasure(measure);
    }

    @GetMapping
    public List<MeasureDTO> getMeasureForDevice(@RequestParam("deviceName") String deviceName,
                                             @RequestParam("period") Period period) {
        List<MeasureDTO> measuresForDevice = measureService.getMeasuresForDevice(deviceName, period);
        return measureService.roundToMinutesAndFillWithZerosMissingData(measuresForDevice, period);
    }
}
