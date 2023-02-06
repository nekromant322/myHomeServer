package com.override.myhomeserver.service;

import com.override.myhomeserver.model.Sensor;
import com.override.myhomeserver.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SensorService {


    @Autowired
    private SensorRepository sensorRepository;

    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }
}
