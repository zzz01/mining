package com.hust.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hust.service.UserService;

@Controller
@RequestMapping("/")
public class AuthController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(value = "form-username", required = true) String userName,
            @RequestParam(value = "form-password", required = true) String passwd, HttpServletRequest request) {
        // ModelAndView mav = new ModelAndView();
        if (userService.login(userName, passwd)) {
            request.getSession().setAttribute("username", userName);
            // mav.setViewName("page/upload.html");
            // return mav;
            return "redirect:page/upload.html";
        }
        // mav.setViewName("page/error.html");
        // return mav;
        return "redirect:page/error.html";
    }
}
