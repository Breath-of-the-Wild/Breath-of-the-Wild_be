package com.breath_of_the_wild_be.service.imageFileService;

import com.breath_of_the_wild_be.domain.ImageFile;
import com.breath_of_the_wild_be.repository.imageFileRepository.ImageFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class ImageFileServiceImpl implements ImageFileService {

    @Autowired
    private ImageFileRepository imageFileRepository;

    @Value("${spring.servlet.multipart.location}")
    private String uploadDir;

    public ImageFile saveImage(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);

        // 디렉토리가 없으면 생성
        Files.createDirectories(filePath.getParent());

        // 파일 저장
        Files.write(filePath, file.getBytes());

        ImageFile imageFile = new ImageFile();
        imageFile.setFileName(fileName);
        imageFile.setFileType(file.getContentType());
        imageFile.setFilePath(filePath.toString());

        return imageFileRepository.save(imageFile);
    }

    @Override
    public byte[] getImage(String fileName) throws IOException {
        Path filePath = Paths.get(uploadDir + fileName);
        return Files.readAllBytes(filePath);
    }
}
