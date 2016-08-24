package com.hust.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.ansj.util.FilterModifWord;

import com.hust.service.SegmentService;

public class SegmentServiceImpl implements SegmentService {

    @Override
    public String[] getSegresult(String str) {
        List<Term> res = ToAnalysis.parse(str);
        res = FilterModifWord.modifResult(res);
        String[] words = new String[res.size()];
        for (int i = 0; i < res.size(); i++) {
            words[i] = res.get(i).getName();
        }
        return words;
    }

    @Override
    public List<String[]> getSegresult(List<String> list) {
        if (null == list) {
            throw new IllegalArgumentException();
        }
        List<String[]> result = new ArrayList<String[]>();
        for (String str : list) {
            String[] array = getSegresult(str);
            result.add(array);
        }
        return result;
    }

    @Override
    public List<List<Object>> getSegresult(List<String[]> list, int index) {
        if (null == list) {
            throw new NullPointerException();
        }
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        List<List<Object>> relist = new ArrayList<List<Object>>();
        for (String[] array : list) {
            List<Object> templist = new ArrayList<Object>();
            for (int i = 0; i < array.length; i++) {
                if (i == index) {
                    String[] temparray = getSegresult(array[i]);
                    templist.add(temparray);
                } else {
                    templist.add(array[i]);
                }
            }
            relist.add(templist);
        }
        return relist;
    }

    @Override
    public List<String[]> getSegresult(String[] array) {
        if (null == array) {
            throw new NullPointerException();
        }
        List<String[]> list = new ArrayList<String[]>();
        if (array.length == 0) {
            return list;
        }
        for (String str : array) {
            String[] temparray = getSegresult(str);
            list.add(temparray);
        }
        return list;
    }

}
