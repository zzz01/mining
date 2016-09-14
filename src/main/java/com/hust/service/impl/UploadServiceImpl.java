package com.hust.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hust.dao.IssueInfoDao;
import com.hust.model.IssueInfo;
import com.hust.service.UploadService;
import com.hust.util.ExcelUtil;

@Service
public class UploadServiceImpl implements UploadService {
    /**
     * Logger for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(UploadServiceImpl.class);

    @Autowired
    private IssueInfoDao issueInfoDao;

    @Override
    public List<String[]> readDataFromExcel(MultipartFile file, String sourceType, String userName) {
        List<String[]> list = new ArrayList<String[]>();
        InputStream is = null;
        try {
            is = file.getInputStream();
            list = ExcelUtil.read(is);
        } catch (IOException e) {
            LOG.error("读取文件出现异常\t" + e.toString());
            return list;
        }
//        IssueInfo issueInfo = new IssueInfo();
//        issueInfo.setIssueId(UUID.randomUUID().toString());
//        issueInfo.setIssueName(file.getName());
//        issueInfo.setSourceType(sourceType);
//        issueInfo.setCreator(userName);
//        issueInfo.setCreateTime(new Date());
//        int count = issueInfoDao.insert(issueInfo);
//        if (count < 1) {
//            LOG.error("向issue_info中插入一条数据失败");
//        }
        return list;
    }
}
