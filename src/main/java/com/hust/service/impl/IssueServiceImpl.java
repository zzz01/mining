package com.hust.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.hust.constants.Constants;
import com.hust.constants.Constants.Index;
import com.hust.dao.IssueDao;
import com.hust.model.Issue;
import com.hust.model.IssueQueryCondition;
import com.hust.model.IssueWithBLOBs;
import com.hust.service.FileService;
import com.hust.service.IssueService;
import com.hust.service.UserService;
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
    private UserService userService;
    @Autowired
    private FileService fileService;

    @Override
    public int createIssue(IssueWithBLOBs issue) {
        // TODO Auto-generated method stub
        return issueDao.insert(issue);
    }

    @Override
    public int combineFiles(String issueId, String user) {
        // TODO Auto-generated method stub
        try {
            List<String[]> oldlist = fileService.combineFilesContentOnSameIssueId(issueId);
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

    @Override
    public List<Issue> queryIssue(IssueQueryCondition con) {
        // TODO Auto-generated method stub
        return issueDao.queryIssue(con);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean combineModifiedCountResult(int[] indexes, HttpServletRequest request) {
        String issueId = request.getSession().getAttribute(Constants.ISSUE_ID).toString();
        List<List<String[]>> resultList = null;
        try {
            resultList = (List<List<String[]>>) ConvertUtil
                    .convertBytesToObject(issueDao.selectByUUID(issueId).getClusterResult());
        } catch (Exception e) {
            return false;
        }
        List<String[]> combineList = new ArrayList<String[]>();
        for (int i = 0; i < indexes.length; i++) {
            combineList.addAll(resultList.get(indexes[i]));
        }
        Arrays.sort(indexes);
        for (int i = indexes.length - 1; i > 0; i--) {
            resultList.remove(indexes[i]);
        }
        resultList.add(combineList);
        Collections.sort(resultList, new Comparator<List<String[]>>() {

            @Override
            public int compare(List<String[]> o1, List<String[]> o2) {
                // TODO Auto-generated method stub
                return o1.size() - o2.size();
            }
        });
        IssueWithBLOBs issue = new IssueWithBLOBs();
        issue.setIssueId(issueId);
        try {
            issue.setModifiedClusterResult(ConvertUtil.convertToBytes(resultList));
            issue.setLastOperator(userService.getCurrentUser(request));
            issue.setLastUpdateTime(new Date());
            if (issueDao.updateIssueInfo(issue) == 0) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public boolean combineOrigCountResult(int indexes, HttpServletRequest request) {
        // TODO Auto-generated method stub
        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean deleteItemsFromModifiedClusterResult(int currentset, int[] indexes, HttpServletRequest request) {
        String issueId = request.getSession().getAttribute(Constants.ISSUE_ID).toString();
        try {
            List<List<String[]>> allList = (List<List<String[]>>) ConvertUtil
                    .convertBytesToObject(issueDao.selectByUUID(issueId).getModifiedClusterResult());
            List<String[]> setList = allList.get(currentset);
            Arrays.sort(indexes);
            for (int i = indexes.length - 1; i > 0; i--) {
                setList.remove(i);
            }
            allList.set(currentset, setList);
            IssueWithBLOBs issue = new IssueWithBLOBs();
            issue.setIssueId(issueId);
            issue.setModifiedClusterResult(ConvertUtil.convertToBytes(allList));
            issue.setLastOperator(userService.getCurrentUser(request));
            issue.setLastUpdateTime(new Date());
            if (0 == issueDao.updateIssueInfo(issue)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public IssueWithBLOBs getIssueById(String uuid) {
        // TODO Auto-generated method stub
        return issueDao.selectByUUID(uuid);
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean deleteItemsFromOrigClusterResult(@RequestParam(value = "currentset", required = true) int currentset,
            @RequestParam(value = "indexes", required = true) int[] indexes, HttpServletRequest request) {
        String issueId = getCurrentIssueId(request);
        try {
            List<List<String[]>> allList =
                    (List<List<String[]>>) ConvertUtil.convertBytesToObject(getIssueById(issueId).getClusterResult());
            List<String[]> setList = allList.get(currentset);
            Arrays.sort(indexes);
            for (int i = indexes.length - 1; i > 0; i--) {
                setList.remove(i);
            }
            allList.set(currentset, setList);
            IssueWithBLOBs issue = new IssueWithBLOBs();
            issue.setIssueId(issueId);
            issue.setModifiedClusterResult(ConvertUtil.convertToBytes(allList));
            issue.setLastOperator(userService.getCurrentUser(request));
            issue.setLastUpdateTime(new Date());
            if (0 == issueDao.updateIssueInfo(issue)) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public int updateIssueInfo(IssueWithBLOBs issue, HttpServletRequest request) {
        // TODO Auto-generated method stub
        String user = userService.getCurrentUser(request);
        issue.setLastOperator(user);
        issue.setLastUpdateTime(new Date());
        return issueDao.updateIssueInfo(issue);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<List<String[]>> queryClusterResult(String issueId) {
        // TODO Auto-generated method stub
        try {
            List<List<String[]>> result = (List<List<String[]>>) ConvertUtil
                    .convertBytesToObject(issueDao.selectByUUID(issueId).getClusterResult());
            return result;
        } catch (Exception e) {
            logger.info("query cluster result from DB failed, issueId:{} \t" + e.toString(), issueId);
            return null;
        }
    }

}
