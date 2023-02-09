package com.override.myhomeserver.model;

import com.override.myhomeserver.enums.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Socket {
    @Id
    private String name;

    @Column
    private DeviceStatus deviceStatus;

    @Column
    private String deviceId;


    //todo подумать как хранить связанные сценарии

}
