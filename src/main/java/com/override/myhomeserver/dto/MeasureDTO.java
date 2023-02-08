package com.override.myhomeserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
public class MeasureDTO {
    private String deviceName;
    private double value;
    private LocalDateTime dateTime;
}
