package com.override.myhomeserver.service.camera;

import com.override.myhomeserver.constants.CameraType;
import com.override.myhomeserver.model.Camera;
import org.springframework.stereotype.Component;

@Component
public class HTTPUrlStrategy implements UrlGenerationStrategy {

    @Override
    public String generateUrl(Camera camera) {
        return "http://" + camera.getInfo().getHost() + ":" + camera.getInfo().getPort();
    }

    @Override
    public CameraType getType() {
        return CameraType.HTTP;
    }
}
