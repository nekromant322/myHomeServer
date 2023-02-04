package com.override.myhomeserver.controller.rest;

import com.override.myhomeserver.model.Camera;
import com.override.myhomeserver.repository.CameraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasureRestController {

    @Autowired
    private CameraRepository cameraRepository;

    @PutMapping("/camera")
    public void putNewCamera(@RequestBody Camera camera) {
        cameraRepository.save(camera);
    }


}
