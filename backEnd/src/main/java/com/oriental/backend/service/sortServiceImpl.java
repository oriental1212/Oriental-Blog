package com.oriental.backend.service;

import com.oriental.backend.dao.SortDao;
import com.oriental.backend.pojo.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class sortServiceImpl implements SortService{
    @Autowired
    private SortDao sortDao;
    @Override
    public List<String> allSortName() {
        return sortDao.allSortName();
    }
    @Override
    public List<Sort> allSort(){
        return sortDao.allSort();
    };
    @Override
    public String findSortNameById(int id){
        return sortDao.findSortNameById(id);
    };
    @Override
    public int deleteSortByName(String sortname){
        return sortDao.deleteSortByName(sortname);
    }
    @Override
    public int updateSort(String sortdescription,String sortname){
        return sortDao.updateSort(sortdescription,sortname);
    }
}
