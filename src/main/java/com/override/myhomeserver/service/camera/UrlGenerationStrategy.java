package com.override.myhomeserver.service.camera;

import com.override.myhomeserver.constants.CameraType;
import com.override.myhomeserver.model.Camera;

public interface UrlGenerationStrategy {

    String generateUrl(Camera camera);
    CameraType getType();
}
