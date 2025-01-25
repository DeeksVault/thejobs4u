package com.DeeksVault.SpringBoot.resume.implementation;

import com.DeeksVault.SpringBoot.resume.ResumeService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ResumeServiceImplementation implements ResumeService {


        @Value("${file.upload-dir}")
        private String uploadDir;

        public String saveOrUpdateResume(MultipartFile file, Long userId) throws IOException {

            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = userId + ".pdf";
            Path filePath = uploadPath.resolve(fileName);

            Files.write(filePath, file.getBytes());

            return filePath.toString();
        }
    }

