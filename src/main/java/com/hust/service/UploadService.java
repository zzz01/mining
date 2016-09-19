package com.hust.service;

import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    List<String[]> readDataFromExcel(MultipartFile file);

    List<String[]> readDataFromExcel(MultipartFile file, String sourceType, String userName);
}
