package com.hust.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hust.cluster.Canopy;
import com.hust.constants.Config;
import com.hust.convertor.Convertor;
import com.hust.convertor.TFIDFConvertor;
import com.hust.service.ClusterService;
import com.hust.service.SegmentService;
import com.hust.util.ConvertUtil;

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
        List<String[]> segmentList = segmentService.getSegresult(list, targetIndex, 1);
        Convertor convertor = new TFIDFConvertor();
        convertor.setList(segmentList);
        List<double[]> vectors = convertor.getVector();
        Canopy canopy = new Canopy();
        canopy.setVectors(vectors);
        canopy.setThreshold(Config.SIMILARITYTHRESHOLD);
        try {
            canopy.clustering();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            logger.error("error occur during clustering" + e.toString());
            return null;
        }
        List<List<Integer>> resultIndexSetList = canopy.getResultIndex();
        return ConvertUtil.convertToStringSet(list, resultIndexSetList, targetIndex);
    }

}
