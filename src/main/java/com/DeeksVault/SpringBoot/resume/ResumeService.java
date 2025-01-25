package com.DeeksVault.SpringBoot.resume;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ResumeService {
    String saveOrUpdateResume(MultipartFile file, Long userId) throws IOException;
}
