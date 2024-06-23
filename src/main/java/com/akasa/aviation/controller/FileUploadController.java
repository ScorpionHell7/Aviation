package com.akasa.aviation.controller;

import com.akasa.aviation.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/upload")
public class FileUploadController {

    @Autowired
    private ExcelService excelService;

    @PostMapping("/excel")
    public ResponseEntity<String> uploadExcelFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a file!");
        }

        try {
            excelService.save(file);
            return ResponseEntity.ok("File uploaded and data saved to database successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to upload and save data: " + e.getMessage());
        }
    }
}