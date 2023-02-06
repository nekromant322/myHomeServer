package com.override.myhomeserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    private String deviceName;

    @Column
    private Double minValue;

    @Column
    private Double maxValue;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "sensor")
    private List<Measure> measures;
}
