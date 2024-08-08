package com.sky.service;

import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.result.PageResult;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;

import java.util.List;

/**
 * ClassName: SetmealService
 * Package: com.sky.service.impl
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/6 14:14
 * @Version 1.0
 */
public interface SetmealService {
    PageResult getMeal(SetmealPageQueryDTO setmealPageQueryDTO);

    SetmealVO getMealById(Long id);


    void addMeal(SetmealDTO setmealDTO);

    void updateMeal(SetmealDTO setmealDTO);

    void batchDelMeal(List<Long> ids);


    List<Setmeal> list(Setmeal setmeal);

    List<DishItemVO> getDishItemById(Long id);
}
