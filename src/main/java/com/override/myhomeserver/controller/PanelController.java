package com.override.myhomeserver.controller;

import com.override.myhomeserver.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/panel")
public class PanelController {

    @Autowired
    private CameraService cameraService;

    @GetMapping("/camera")
    public ModelAndView getCameraPanel() {
        ModelAndView modelAndView = new ModelAndView("cameraPanel");
        modelAndView.addObject("cameras", cameraService.findAll());
        return modelAndView;
    }
}
