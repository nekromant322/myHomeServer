package com.override.myhomeserver.controller.rest;

import com.override.myhomeserver.dto.CameraDTO;
import com.override.myhomeserver.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/camera")
public class CameraRestController {

    @Autowired
    private CameraService cameraService;

    @PutMapping
    public void putNewCamera(@RequestBody CameraDTO camera) {
        cameraService.save(camera);
    }

}
