package com.hust.service;

import java.io.InputStream;
import java.util.List;

public interface UploadService {
    public List<String[]> readDataFromExcel(InputStream is);
}
