package com.hust.service;

import java.util.List;

public interface SegmentService {

    public String[] getSegresult(String str);

    public List<String[]> getSegresult(List<String> list);

    public List<List<Object>> getSegresult(List<String[]> list, int index);
    
    public List<String[]> getSegresult(String[] array);
}
