package com.hust.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hust.model.IssueWithBLOBs;
import com.hust.service.IssueService;
import com.hust.service.UserService;
import com.hust.util.ResultUtil;

@Controller
@RequestMapping("/issue")
public class IssueController {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(IssueController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private IssueService issueService;

    @ResponseBody
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Object createIssue(@RequestParam(value = "issue_name", required = true) String issueName,
            HttpServletRequest request) {
        String user = userService.getCurrentUser(request);
        IssueWithBLOBs issue = new IssueWithBLOBs();
        issue.setIssueId(UUID.randomUUID().toString());
        issue.setIssueName(issueName);
        issue.setCreator(user);
        issue.setCreateTime(new Date());
        issue.setLastOperator(user);
        if (issueService.createIssue(issue) == 0) {
            logger.info("create issue fail");
            return ResultUtil.errorWithMsg("create issue fail");
        }
        return ResultUtil.success("create issue success");
    }

    @ResponseBody
    @RequestMapping(value = "/shuffle", method = RequestMethod.POST)
    public Object shuffle(HttpServletRequest request) {
        String issueId = issueService.getCurrentIssueId(request);
        if (StringUtils.isBlank(issueId)) {
            return ResultUtil.errorWithMsg("query current issue failed,please create or select a issue");
        }
        String user = userService.getCurrentUser(request);
        if (issueService.combineFiles(issueId, user) == 0) {
            return ResultUtil.errorWithMsg("combine different files failed");
        }
        return ResultUtil.success("combine success");
    }

}
