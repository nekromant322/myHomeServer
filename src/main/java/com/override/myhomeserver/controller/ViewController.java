package com.override.myhomeserver.controller;

import com.override.myhomeserver.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    private CameraService cameraService;

    @GetMapping("/oneMeasure")
    public String getCameraPanel() {
        return "oneMeasure";
    }
}
