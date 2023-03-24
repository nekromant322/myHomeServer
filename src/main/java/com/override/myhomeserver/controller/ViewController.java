package com.override.myhomeserver.controller;

import com.override.myhomeserver.dto.CameraDTO;
import com.override.myhomeserver.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {

    @Autowired
    private CameraService cameraService;

    @GetMapping("/oneMeasure")
    public String getOneMeasure() {
        return "oneMeasure";
    }

    @GetMapping("/oneCamera")
    public ModelAndView getOneCamera(@RequestParam("name") String name) {
        CameraDTO camera = cameraService.findAll().stream().filter(c -> c.getName().equals(name)).findFirst().get();
        ModelAndView modelAndView = new ModelAndView("oneCamera");
        modelAndView.addObject("url",camera.getUrl());
        modelAndView.addObject("name",camera.getName());
        return modelAndView;
    }
}
