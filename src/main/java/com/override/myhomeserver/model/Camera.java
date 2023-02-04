package com.override.myhomeserver.model;

import com.override.myhomeserver.constants.CameraType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Camera {
    @EmbeddedId
    private BasicWebDeviceInfo info;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private CameraType type;
}
