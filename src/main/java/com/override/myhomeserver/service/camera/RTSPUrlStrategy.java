package com.override.myhomeserver.service.camera;

import com.override.myhomeserver.constants.CameraType;
import com.override.myhomeserver.model.Camera;
import org.springframework.stereotype.Component;

@Component
public class RTSPUrlStrategy implements UrlGenerationStrategy {

    @Override
    public String generateUrl(Camera camera) {
        return "rtsp://" + camera.getLogin() + ":" + camera.getPassword() + "@" + camera.getHost() + ":" + camera.getPort();
    }

    @Override
    public CameraType getType() {
        return CameraType.RTSP;
    }
}
