package com.sky.service;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;

import java.util.List;

/**
 * ClassName: CategoryService
 * Package: com.sky.service
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/6/30 14:50
 * @Version 1.0
 */
public interface CategoryService {
    PageResult getCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    void addCategory(CategoryDTO categoryDTO);

    void updateCategory(CategoryDTO categoryDTO);

    void updateStatus(Integer status, Long id);

    void delCategory(Long id);

    List<Category> getCategoryByType(Integer type);
}
