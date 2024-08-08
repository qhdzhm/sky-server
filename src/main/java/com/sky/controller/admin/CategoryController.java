package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin/category")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/page")
    public Result<PageResult> getCategoryList(CategoryPageQueryDTO categoryPageQueryDTO){
        PageResult pageResult = categoryService.getCategory(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result addCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }
    @PutMapping
    public Result updateCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    @PostMapping("/status/{status}")
    public Result updateStatus(@PathVariable Integer status,Long id){
        categoryService.updateStatus(status,id);
        return Result.success();
    }

    @DeleteMapping
    public Result delCategory(Long id){
        categoryService.delCategory(id);
        return Result.success();
    }
    @GetMapping("/list")
    public Result<List<Category>> getCategoryByType(Integer type){
        List<Category> categoryList = categoryService.getCategoryByType(type);
        return Result.success(categoryList);
    }

}
