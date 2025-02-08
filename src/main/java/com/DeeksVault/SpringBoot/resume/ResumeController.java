package com.DeeksVault.SpringBoot.resume;

import com.DeeksVault.SpringBoot.user.User;
import com.DeeksVault.SpringBoot.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/users")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @Autowired
    private UserService userService;


    @PostMapping("/{userId}/resume/upload")
    public ResponseEntity<String> uploadResume(
            @PathVariable Long userId,
            @RequestParam("file") MultipartFile file
    ) {
        System.out.println("in upload controller");
        try {

            if (!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().body("Only PDF files are allowed.");
            }


            String filePath = resumeService.saveOrUpdateResume(file, userId);


            userService.updateResumePath(userId, filePath);

            return ResponseEntity.ok("Resume uploaded successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload resume.");
        }
    }


    @GetMapping("/{userId}/resume")
    public ResponseEntity<String> getResumePath(@PathVariable Long userId) {
        User user = userService.getUser(userId);
        if (user.getResumePath() != null) {
            return ResponseEntity.ok(user.getResumePath());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resume not found for user.");
        }
    }
}

