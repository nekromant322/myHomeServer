package com.override.myhomeserver.mapper;

import com.override.myhomeserver.dto.CameraDTO;
import com.override.myhomeserver.model.BasicWebDeviceInfo;
import com.override.myhomeserver.model.Camera;
import org.springframework.stereotype.Component;

@Component
public class CameraMapper {

    public CameraDTO mapToDTO(Camera camera, String url) {
        return CameraDTO.builder()
                .name(camera.getInfo().getName())
                .host(camera.getInfo().getHost())
                .port(camera.getInfo().getPort())
                .login(camera.getLogin())
                .password(camera.getPassword())
                .type(camera.getType())
                .url(url)
                .build();
    }
    public Camera mapToEntity(CameraDTO cameraDTO) {
        return Camera.builder()
                .info(BasicWebDeviceInfo.builder()
                        .name(cameraDTO.getName())
                        .host(cameraDTO.getHost())
                        .port(cameraDTO.getPort())
                        .build())
                .login(cameraDTO.getLogin())
                .password(cameraDTO.getPassword())
                .type(cameraDTO.getType())
                .build();
    }
}
