package com.hust.service;

import javax.servlet.http.HttpServletRequest;

import com.hust.model.Condition;

public interface FileService {
    int insert(Condition con, HttpServletRequest request);

    int deleteById(String fileId);
}
