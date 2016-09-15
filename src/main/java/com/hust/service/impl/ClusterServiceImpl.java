package com.hust.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.cluster.Canopy;
import com.hust.convertor.Convertor;
import com.hust.convertor.TFIDFConvertor;
import com.hust.service.ClusterService;
import com.hust.service.SegmentService;

@Service
public class ClusterServiceImpl implements ClusterService {
    /**
     * Logger for this class
     */
    private static final Logger logger = LoggerFactory.getLogger(ClusterServiceImpl.class);

    @Autowired
    private SegmentService segmentService;

    @Override
    public List<List<String[]>> getClusterResult(List<String[]> list, int targetIndex) {
        // TODO Auto-generated method stub
        List<List<Object>> segmentList = segmentService.getSegresult(list, targetIndex);
        List<String[]> segment = new ArrayList<String[]>();
        for (List<Object> templist : segmentList) {
            segment.add((String[]) templist.get(targetIndex));
        }
        Convertor convertor = new TFIDFConvertor();
        convertor.setList(segment);
        List<double[]> vectors = convertor.getVector();
        Canopy canopy = new Canopy();
        canopy.setVectors(vectors);
        canopy.setThreshold(0.4f);
        try {
            canopy.clustering();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("error occur during clustering" + e.toString());
            return null;
        }
        List<List<Integer>> resultIndexSetList = canopy.getResultIndex();
        return convertToStringSet(list, resultIndexSetList, targetIndex);
    }

    private List<List<String[]>> convertToStringSet(List<String[]> list, List<List<Integer>> resultIndexSet,
            final int targetIndex) {
        if (null == resultIndexSet) {
            return null;
        }
        List<List<String[]>> listStrSet = new ArrayList<List<String[]>>();
        List<String[]> singleDataList = new ArrayList<String[]>();
        for (List<Integer> set : resultIndexSet) {
            if (set.size() == 1) {
                singleDataList.add(list.get(set.get(0)));
                continue;
            }
            List<String[]> setDataList = new ArrayList<String[]>();
            for (int i : set) {
                setDataList.add(list.get(i));
            }
            Collections.sort(setDataList, new Comparator<String[]>() {
                public int compare(String[] o1, String[] o2) {
                    return o1[targetIndex].compareTo(o2[targetIndex]);
                }
            });
            listStrSet.add(setDataList);
        }

        Collections.sort(singleDataList, new Comparator<String[]>() {
            public int compare(String[] o1, String[] o2) {
                return o1[targetIndex].compareTo(o2[targetIndex]);
            }
        });
        listStrSet.add(singleDataList);
        Collections.sort(listStrSet, new Comparator<List<String[]>>() {

            @Override
            public int compare(List<String[]> o1, List<String[]> o2) {
                // TODO Auto-generated method stub
                return o2.size() - o1.size();
            }
        });
        return listStrSet;
    }
}
