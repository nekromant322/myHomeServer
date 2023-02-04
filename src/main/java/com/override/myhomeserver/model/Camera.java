package com.override.myhomeserver.model;

import com.override.myhomeserver.constants.CameraType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Camera {
    @Id
    private String name;
    @Column
    private String host;
    @Column
    private String port;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private CameraType type;
}
