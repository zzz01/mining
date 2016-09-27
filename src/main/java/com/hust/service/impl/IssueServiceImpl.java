package com.hust.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.constants.Constants;
import com.hust.constants.Constants.Index;
import com.hust.dao.FileDao;
import com.hust.dao.IssueDao;
import com.hust.model.IssueWithBLOBs;
import com.hust.service.IssueService;
import com.hust.util.ConvertUtil;

@Service
public class IssueServiceImpl implements IssueService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(IssueServiceImpl.class);

    @Autowired
    private IssueDao issueDao;

    @Autowired
    private FileDao fileDao;

    @Override
    public IssueWithBLOBs getById(String UUID) {
        return issueDao.selectByUUID(UUID);
    }

    @Override
    public int updateIssueInfo(IssueWithBLOBs issue) {
        return issueDao.updateIssueInfo(issue);
    }

    @Override
    public int createIssue(IssueWithBLOBs issue) {
        // TODO Auto-generated method stub
        return issueDao.insert(issue);
    }

    @Override
    public int combineFiles(String issueId, String user) {
        // TODO Auto-generated method stub
        try {
            List<String[]> oldlist = fileDao.queryContents(issueId);
            List<String[]> list = new ArrayList<String[]>();
            List<String> urllist = new ArrayList<String>();
            for (String[] row : oldlist) {
                int exitIndex = urllist.indexOf(row[Index.URL_INDEX]);
                if (exitIndex != -1) {
                    if (row[Index.TIME_INDEX].compareTo(list.get(exitIndex)[Index.TIME_INDEX]) < 0) {
                        list.set(exitIndex, row);
                    }
                } else {
                    list.add(row);
                    urllist.add(row[Index.URL_INDEX]);
                }
            }
            IssueWithBLOBs issue = new IssueWithBLOBs();
            issue.setIssueId(issueId);
            issue.setFilteredContent(ConvertUtil.convertToBytes(list));
            issue.setLastOperator(user);
            return issueDao.updateIssueInfo(issue);
        } catch (Exception e) {
            logger.error("combining files related to this issue:{} failed", issueId);
            return 0;
        }
    }

    @Override
    public String getCurrentIssueId(HttpServletRequest request) {
        // TODO Auto-generated method stub
        Object obj = request.getSession().getAttribute(Constants.ISSUE_ID);
        if (null == obj) {
            return StringUtils.EMPTY;
        }
        return obj.toString();
    }

}
