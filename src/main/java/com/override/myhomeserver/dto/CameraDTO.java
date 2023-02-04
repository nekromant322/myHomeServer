package com.override.myhomeserver.dto;

import com.override.myhomeserver.constants.CameraType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CameraDTO {
    private String name;
    private String host;
    private String port;
    private String login;
    private String password;
    private CameraType type;
    private String url;
}
