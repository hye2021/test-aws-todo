package org.example.todo.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.todo.entity.FileEntity;
import org.example.todo.repository.FileRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileDatabaseService {
    private final FileRepository fileRepository;

    @Transactional
    public FileEntity saveFileMetadata(String uuid, String path, String originalFilename, long size, String mimeType){
        FileEntity fileEntity = new FileEntity();
        fileEntity.setUuid(uuid);
        fileEntity.setPath(path);
        fileEntity.setOriginalFilename(originalFilename);
        fileEntity.setSize(size);
        fileEntity.setMimeType(mimeType);
        return fileRepository.save(fileEntity);
    }
    public Optional<FileEntity> getFileMetadata(String uuid){
        return fileRepository.findByUuid(uuid);
    }
}
