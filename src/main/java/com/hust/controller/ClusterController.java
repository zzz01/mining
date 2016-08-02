package com.hust.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hust.service.ClusterService;
import com.hust.service.UploadService;

@Controller
@RequestMapping("/test")
public class ClusterController {
    /**
     * Logger for this class
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(ClusterController.class);

    @Autowired
    private UploadService uploadService;

    @Autowired
    private ClusterService clusterService;

    @RequestMapping(value = "/cluster", method = RequestMethod.POST)
    public ModelAndView cluster(
            @RequestParam(value = "file", required = true) MultipartFile file,
            @RequestParam(value = "targetIndex", required = true) int targetIndex) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("upload(MultipartFile) - start"); // $NON-NLS-1$
        }

        InputStream is;
        try {
            is = file.getInputStream();
        } catch (IOException e) {
            LOG.error("Upload file error");
            return new ModelAndView("error.jsp");
        }

        List<String[]> list = uploadService.readDataFromExcel(is);
        list = clusterService.getClusterResult(list, targetIndex);
        ModelAndView mav = new ModelAndView("contentShow.jsp");
        mav.addObject("list", list);
        return mav;
    }
}
