package com.hust.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatus;

import com.hust.constants.Result;

import net.sf.json.JSONObject;

public class ResultUtil {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    public static Object success(Object result) {
        JSONObject object = new JSONObject();
        object.put("status", HttpStatus.OK);
        object.put("result", result);
        // logger.info("status：{}，result：{}", HttpStatus.OK, result.toString());
        //logger.info("结果\t" + object.toString());
        return object;
    }

    public static Object successWithoutStatus(Object result) {
        JSONObject object = new JSONObject();
        object.put("result", result);
        logger.info("结果\t" + object.toString());
        return object;
    }

    public static Object unknowError() {
        JSONObject object = new JSONObject();
        object.put("status", Result.ERROR_CODE);
        object.put("result", Result.UNKNOW_ERROR);
        logger.info("结果\t" + object);
        return object;
    }

    public static Object errorWithMsg(Object msg) {
        JSONObject object = new JSONObject();
        object.put("status", Result.ERROR_CODE);
        object.put("result", msg);
        logger.info("结果状态：{}，结果内容：{}", Result.ERROR_CODE, msg);
        return object;
    }
}
