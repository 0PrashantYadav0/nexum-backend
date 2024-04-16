package com.pixels.Nexum.repository;

import com.pixels.Nexum.model.FileModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileModel, Integer> {
}