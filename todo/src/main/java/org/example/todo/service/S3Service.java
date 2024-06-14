package org.example.todo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final S3Client s3client;

    private final String backetName = "hye2021-s3-bucket";
    public void uploadFile(MultipartFile file, String key) throws IOException {
        // 파일 이름을 key로 사용
        // String key = file.getOriginalFilename();

        // uuid 불러와서 key로 사용 -> 매개변수에 key 추가

        s3client.putObject(
                PutObjectRequest.builder()
                        .bucket(backetName)
                        .key(key)
                        .build(),
                RequestBody.fromBytes(file.getBytes())
        );
    }

    public InputStream downloadFile(String key) {
        return s3client.getObject(
                GetObjectRequest.builder()
                        .bucket(backetName)
                        .key(key)
                        .build()
        );
    }
}
