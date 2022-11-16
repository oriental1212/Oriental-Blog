package com.oriental.backend.dao;

import com.oriental.backend.pojo.Article;
import com.oriental.backend.pojo.Sort;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SortDao {
    List<String> allSortName();
    List<Sort> allSort();
    String findSortNameById(int id);
    int deleteSortByName(String sortname);
    int updateSort(String sortdescription,String sortname);
}
