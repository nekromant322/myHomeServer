package com.override.myhomeserver.repository;

import com.override.myhomeserver.model.Camera;
import com.override.myhomeserver.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<Sensor, String> {
}
