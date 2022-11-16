package com.oriental.backend.service;

import com.oriental.backend.pojo.Sort;

import java.util.List;

public interface SortService {
    List<String> allSortName();
    List<Sort> allSort();
    String findSortNameById(int id);
    int deleteSortByName(String sortname);
    int updateSort(String sortdescription,String sortname);
}
