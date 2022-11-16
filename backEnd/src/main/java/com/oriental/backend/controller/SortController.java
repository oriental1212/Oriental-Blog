package com.oriental.backend.controller;

import com.oriental.backend.confirgure.Result;
import com.oriental.backend.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class SortController {
    @Autowired
    private SortService sortService;

    @GetMapping("/sort/allsortname")
    public Result allsortname(){
        return Result.success(sortService.allSortName());
    }
    @GetMapping("/sort/allsort")
    public Result allsort(){
        return Result.success(sortService.allSort());
    }
    @DeleteMapping("/sort/deletesortbyname/{name}")
    public Result deleteSortByName(@PathVariable String name){
        return Result.success(sortService.deleteSortByName(name));
    }
    @PutMapping("/sort/updateSort/{sortname}/{sortdescription}")
    public Result updateSort(@PathVariable String sortdescription,@PathVariable String sortname){
        return Result.success(sortService.updateSort(sortdescription,sortname));
    }
}
