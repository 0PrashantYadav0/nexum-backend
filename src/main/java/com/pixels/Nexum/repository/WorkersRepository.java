package com.pixels.Nexum.repository;

import com.pixels.Nexum.model.WorkerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkersRepository extends JpaRepository<WorkerModel, Integer> {
}
