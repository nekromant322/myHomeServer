package com.override.myhomeserver.controller.rest;

import com.override.myhomeserver.model.Sensor;
import com.override.myhomeserver.model.Socket;
import com.override.myhomeserver.repository.SocketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socket")
public class SocketRestController {

    @Autowired
    private SocketRepository socketRepository;

//    @GetMapping
//    public Socket getSocket(@RequestParam("name") String name) {
//        return null;
//    }

    @GetMapping
    public List<Socket> getAllSockets() {
        return socketRepository.findAll();
    }

    @PutMapping
    public void putSocket(@RequestBody Socket socket) {
        socketRepository.save(socket);
    }
}
