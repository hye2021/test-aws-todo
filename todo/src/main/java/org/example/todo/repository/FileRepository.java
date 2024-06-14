package org.example.todo.repository;

import org.example.todo.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileRepository extends JpaRepository<FileEntity, Long>{
    Optional<FileEntity> findByUuid(String uuid);
}
