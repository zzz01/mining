package com.hust.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hust.constants.Constants;
import com.hust.model.IssueInfoWithBLOBs;
import com.hust.service.IssueService;
import com.hust.util.ConvertUtil;
import com.hust.util.ExcelUtil;
import com.hust.util.ResultUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Controller
@RequestMapping("/file")
public class FileController {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IssueService issueService;

    @ResponseBody
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Object upload(@RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "sourceType", required = true) String sourceType, HttpServletRequest request) {
        if (file.isEmpty()) {
            return ResultUtil.errorWithMsg("文件为空");
        }
        List<String[]> list = null;
        try {
            list = issueService.readAndSave(file, sourceType, request);
            if (null == list || list.size() == 0) {
                return ResultUtil.errorWithMsg("文件是空的");
            }
        } catch (Exception e) {
            logger.info(e.toString());
            return ResultUtil.errorWithMsg(e.toString());
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

    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Object uuidObj = request.getSession().getAttribute(Constants.ISSUE_ID);
        String uuid = uuidObj == null ? StringUtils.EMPTY : uuidObj.toString();
        if (StringUtils.isBlank(uuid)) {
            response.sendError(404, "未找到文件，请先上传");
            logger.info("从session中无法获得文件uuid");
            return;
        }
        OutputStream outputStream = null;
        try {
            IssueInfoWithBLOBs issue = issueService.getByUUID(uuid);
            List<String[]> list = (List<String[]>) ConvertUtil.convertBytesToObject(issue.getResultList());
            outputStream = response.getOutputStream();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=result.xls");
            HSSFWorkbook workbook = ExcelUtil.exportToExcel(list);
            workbook.write(outputStream);
        } catch (Exception e) {
            logger.info("excel 导出失败\t" + e.toString());
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                logger.info("导出excel时，关闭outputstream失败");
            }
        }
    }
}
