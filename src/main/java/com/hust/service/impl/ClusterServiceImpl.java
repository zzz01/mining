package com.hust.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hust.datahandle.Cluster;
import com.hust.service.ClusterService;

@Service
public class ClusterServiceImpl implements ClusterService {

	@Override
	public List<String[]> getClusterResult(List<String[]> list, int targetIndex) {
		// TODO Auto-generated method stub
		Cluster cluster = new Cluster(list, targetIndex);
		return cluster.getResult();
	}

}
