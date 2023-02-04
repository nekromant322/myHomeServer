package com.override.myhomeserver.service;

import com.override.myhomeserver.constants.CameraType;
import com.override.myhomeserver.dto.CameraDTO;
import com.override.myhomeserver.mapper.CameraMapper;
import com.override.myhomeserver.model.Camera;
import com.override.myhomeserver.repository.CameraRepository;
import com.override.myhomeserver.service.camera.UrlGenerationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CameraService {

    @Autowired
    private CameraRepository cameraRepository;

    @Autowired
    private CameraMapper cameraMapper;

    @Autowired
    private List<UrlGenerationStrategy> urlStrategies;

    private Map<CameraType, UrlGenerationStrategy> strategyMap = new HashMap<>();

    @PostConstruct
    public void initStrategies() {
        urlStrategies
                .forEach(strategy -> strategyMap.put(strategy.getType(), strategy));
    }

    public List<CameraDTO> findAll() {
        return cameraRepository.findAll()
                .stream()
                .map(camera -> cameraMapper.mapToDTO(camera, generateCameraUrl(camera)))
                .collect(Collectors.toList());

    }

    private String generateCameraUrl(Camera camera) {
        return strategyMap.get(camera.getType()).generateUrl(camera);
    }

    public void save(CameraDTO cameraDTO) {
        cameraRepository.save(cameraMapper.mapToEntity(cameraDTO));
    }
}
