package com.override.myhomeserver.repository;

import com.override.myhomeserver.model.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeasureRepository extends JpaRepository<Measure, UUID> {
}
