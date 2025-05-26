package com.example.hello.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileController {
    @GetMapping("/fileUpload")
    public String uploadFile() {
        // Logic for file upload
        return "fileUpload";
    }

    @PostMapping("/fileUpload")
    public String uploadFile(@RequestParam ("file") MultipartFile file) {
        System.out.println("---------------------------File uploaded: " + file.getOriginalFilename());
        return "fileUpload";
    }
}
