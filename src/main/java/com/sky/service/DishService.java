package com.sky.service;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.vo.DishVO;

import java.util.List;

/**
 * ClassName: DishService
 * Package: com.sky.service
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/1 14:35
 * @Version 1.0
 */
public interface DishService {
    PageResult getDishList(DishPageQueryDTO dishPageQueryDTO);

    void addDishWithFlavour(DishDTO dishDTO);


    DishVO getDishById(Long id);

    void updateDish(DishDTO dishDTO);

    void batchDel(List<Long> ids);

    List<Dish> getDishByCategoryId(Integer categoryId);

    List<DishVO> listWithFlavor(Dish dish);
}
