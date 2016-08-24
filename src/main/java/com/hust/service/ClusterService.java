package com.hust.service;

import java.util.List;

public interface ClusterService {
    List<List<String[]>> getClusterResult(List<String[]> list, int targetIndex);
}
