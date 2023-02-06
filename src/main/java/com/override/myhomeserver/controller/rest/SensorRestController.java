package com.override.myhomeserver.controller.rest;

import com.override.myhomeserver.model.Sensor;
import com.override.myhomeserver.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensor")
public class SensorRestController {

    @Autowired
    private SensorService sensorService;

    @PutMapping
    public void newSensor(@RequestBody Sensor sensor) {
        sensorService.save(sensor);
    }


}
