package com.apps.file_uploader.model;

import org.springframework.web.multipart.MultipartFile;

public class User {
    private String name;
    private MultipartFile file;

    public User() {
    }

    public User(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
