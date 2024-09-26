package com.apps.file_uploader.controller;

import com.apps.file_uploader.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    private static final Logger log = LoggerFactory.getLogger(FileUploadController.class);

    @PostMapping(value = "/requestParam", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUploadFormUsingRequestParam(@RequestParam String name, @RequestPart("file") MultipartFile file) throws IOException {

        log.info("handling request parts: {}", file);

        try {

            File f = new ClassPathResource("").getFile();
            final Path path = Paths.get(f.getAbsolutePath()
                    + File.separator + "static" + File.separator + "image");

            System.out.println(name);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            var result = Map.of(
                    "filename", file.getOriginalFilename(),
                    "fileUri", fileUri
            );

            return ResponseEntity.ok().body(result);

        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/modelAttribute", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> handleFileUploadUsingRequestPart(@ModelAttribute User user) throws IOException {

        MultipartFile file = user.getFile();
        String name = user.getName();

        log.info("handling request parts: {}", file);

        try {

            File f = new ClassPathResource("").getFile();
            final Path path = Paths.get(f.getAbsolutePath()
                    + File.separator + "static" + File.separator + "image");

            System.out.println(name);

            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath = path.resolve(file.getOriginalFilename());
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/image/")
                    .path(file.getOriginalFilename())
                    .toUriString();

            var result = Map.of(
                    "filename", file.getOriginalFilename(),
                    "fileUri", fileUri
            );

            return ResponseEntity.ok().body(result);

        } catch (IOException e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}