package com.override.myhomeserver.repository;

import com.override.myhomeserver.model.Camera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, String> {
}
