package com.hust.service;

import javax.servlet.http.HttpServletRequest;

public interface UserService {

    boolean login(String username, String password);

    String getCurrentUser(HttpServletRequest request);

    void logout(HttpServletRequest request);
}
