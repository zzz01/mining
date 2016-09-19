package com.hust.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hust.service.UploadService;
import com.hust.util.ResultUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/upload")
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @ResponseBody
    @RequestMapping(value = "scanfile", method = RequestMethod.POST)
    public Object scanExcelFile(@RequestParam(value = "file", required = true) MultipartFile file) {
        if (file.isEmpty()) {
            return ResultUtil.errorWithMsg("文件为空");
        }
        List<String[]> list = uploadService.readDataFromExcel(file);
        if (null == list || list.size() == 0) {
            return ResultUtil.errorWithMsg("文件是空的");
        }
        JSONObject result = new JSONObject();
        JSONArray head = new JSONArray();
        JSONArray body = new JSONArray();
        String[] headArray = list.get(0);
        for (String str : headArray) {
            head.add(str);
        }
        int length = list.size() < 10 ? list.size() : 10;
        for (int i = 1; i < length; i++) {
            JSONArray line = new JSONArray();
            String[] lineArray = list.get(i);
            for (String str : lineArray) {
                line.add(str);
            }
            body.add(line);
        }
        result.put("head", head);
        result.put("body", body);
        return ResultUtil.success(result);
    }
}
