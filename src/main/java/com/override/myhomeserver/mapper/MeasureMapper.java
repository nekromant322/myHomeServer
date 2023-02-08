package com.override.myhomeserver.mapper;

import com.override.myhomeserver.dto.MeasureDTO;
import com.override.myhomeserver.model.Measure;
import org.springframework.stereotype.Component;

@Component
public class MeasureMapper {

    public MeasureDTO toDTO(Measure measure) {
        String deviceName = null;
        if(measure.getSensor() != null) {
            deviceName = measure.getSensor().getDeviceName();
        }
        return MeasureDTO.builder()
                .deviceName(deviceName)
                .dateTime(measure.getDateTime())
                .value(measure.getValue())
                .build();
    }


}
