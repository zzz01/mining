package com.hust.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hust.constants.Constants;
import com.hust.dao.IssueInfoDao;
import com.hust.model.IssueInfoWithBLOBs;
import com.hust.service.IssueService;
import com.hust.service.UserService;
import com.hust.util.ConvertUtil;
import com.hust.util.ExcelUtil;

@Service
public class IssueServiceImpl implements IssueService {
    /**
     * Logger for this class
     */
    private static final Logger LOG = LoggerFactory.getLogger(IssueServiceImpl.class);

    @Autowired
    private IssueInfoDao issueInfoDao;

    @Autowired
    private UserService userService;

    @Override
    public List<String[]> readAndSave(MultipartFile file, String sourceType, HttpServletRequest request)
            throws Exception {
        List<String[]> list = readDataFromExcel(file);
        if (null == list) {
            return list;
        }
        String userName = userService.getCurrentUser(request);
        IssueInfoWithBLOBs issueInfo = new IssueInfoWithBLOBs();
        issueInfo.setIssueId(UUID.randomUUID().toString());
        issueInfo.setIssueName(file.getOriginalFilename());
        issueInfo.setCreator(userName);
        issueInfo.setCreateTime(new Date());
        issueInfo.setSourceType(sourceType);
        issueInfo.setContent(ConvertUtil.convertToBytes(list));
        int count = issueInfoDao.insertSelective(issueInfo);
        if (count < 1) {
            throw new Exception("insert data into issue_info error!");
        }
        request.getSession().setAttribute(Constants.ISSUE_ID, issueInfo.getIssueId());
        return list;
    }

    private List<String[]> readDataFromExcel(MultipartFile file) {
        List<String[]> list = new ArrayList<String[]>();
        InputStream is = null;
        try {
            is = file.getInputStream();
            list = ExcelUtil.read(is);
        } catch (IOException e) {
            LOG.error("读取文件出现异常\t" + e.toString());
            return null;
        }
        return list;
    }

    @Override
    public IssueInfoWithBLOBs getByUUID(String UUID) {
        return issueInfoDao.selectByUUID(UUID);
    }

    @Override
    public int updateIssueInfo(IssueInfoWithBLOBs issue) {
        return issueInfoDao.updateIssueInfo(issue);
    }

}
