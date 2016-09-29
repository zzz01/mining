package com.hust.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import com.hust.constants.Constants;
import com.hust.dao.FileDao;
import com.hust.model.Condition;
import com.hust.model.IssueFile;
import com.hust.service.FileService;
import com.hust.service.UserService;
import com.hust.util.ConvertUtil;
import com.hust.util.ExcelUtil;

public class FileServiceImpl implements FileService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private FileDao fileDao;

    @Autowired
    private UserService userService;

    @Override
    public int insert(Condition con, HttpServletRequest request) {
        // TODO Auto-generated method stub
        MultipartFile file = con.getFile();
        List<String[]> list = new ArrayList<String[]>();
        InputStream is = null;
        try {
            is = file.getInputStream();
            // 此处index传入的顺序必须与constants中定义的value值保持一致
            list = ExcelUtil.read(file.getOriginalFilename(), is, con.getUrlIndex(), con.getTitleIndex(),
                    con.getTimeIndex());
        } catch (IOException e) {
            logger.error("读取文件出现异常\t" + e.toString());
            return 0;
        }

        String user = userService.getCurrentUser(request);
        String issueId = request.getSession().getAttribute(Constants.ISSUE_ID).toString();
        IssueFile issueFile = new IssueFile();
        issueFile.setFileId(UUID.randomUUID().toString());
        issueFile.setFileName(file.getOriginalFilename());
        issueFile.setCreator(user);
        issueFile.setIssueId(issueId);
        issueFile.setLineNumber(list.size());
        issueFile.setSize((int) (file.getSize() / 1024));
        issueFile.setSourceType(con.getSourceType());
        try {
            issueFile.setContent(ConvertUtil.convertToBytes(list));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("exception occur during inserting file into DB\t" + e.toString());
            return 0;
        }
        return fileDao.insert(issueFile);
    }

    @Override
    public int deleteById(String fileId) {
        // TODO Auto-generated method stub
        return fileDao.deleteById(fileId);
    }

    @Override
    public List<IssueFile> queryFilesByIssueId(String issueId) {
        // TODO Auto-generated method stub
        return fileDao.queryFilesByIssueId(issueId);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<String[]> combineFilesContentOnSameIssueId(String issueId) {
        // TODO Auto-generated method stub
        List<IssueFile> files = fileDao.queryFilesByIssueId(issueId);
        List<String[]> list = new ArrayList<String[]>();
        try {
            for (IssueFile file : files) {
                List<String[]> content = (List<String[]>) ConvertUtil.convertBytesToObject(file.getContent());
                list.addAll(content);
            }
        } catch (Exception e) {
            logger.error("合并issueid:{}相关的文件失败", issueId);
            return null;
        }
        return list;
    }

}
