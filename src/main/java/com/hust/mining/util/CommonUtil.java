package com.hust.mining.util;

import org.apache.commons.lang.StringUtils;

public class CommonUtil {

    public static boolean isEmptyArray(String[] array) {
        if (null == array || array.length == 0) {
            return true;
        }
        for (int i = 0; i < array.length / 2; i++) {
            if (!StringUtils.isBlank(array[i])) {
                return false;
            }
        }
        return true;
    }
}
