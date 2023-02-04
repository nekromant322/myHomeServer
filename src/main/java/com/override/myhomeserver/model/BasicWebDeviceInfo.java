package com.override.myhomeserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BasicWebDeviceInfo implements Serializable {
    @Column
    private String name;
    @Column
    private String host;
    @Column
    private String port;
}
