package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.constant.MessageConstant;
import com.sky.constant.StatusConstant;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.entity.SetmealDish;
import com.sky.exception.DeletionNotAllowedException;
import com.sky.mapper.SetmealDishMapper;
import com.sky.mapper.SetmealMapper;
import com.sky.result.PageResult;
import com.sky.service.SetmealService;
import com.sky.vo.DishItemVO;
import com.sky.vo.SetmealVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: SetmealServiceImpl
 * Package: com.sky.service.impl
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/6 14:15
 * @Version 1.0
 */
@Service
public class SetmealServiceImpl implements SetmealService {
    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    public PageResult getMeal(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(), setmealPageQueryDTO.getPageSize());

        Page<Setmeal> page = setmealMapper.getMealList(setmealPageQueryDTO);

        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public SetmealVO getMealById(Long id) {
        List<SetmealDish> setmealDishBySetmealId = setmealDishMapper.getSetmealDishBySetmealId(id);

        SetmealVO setmealVO = setmealMapper.getMealById(id);

        setmealVO.setSetmealDishes(setmealDishBySetmealId);
        return setmealVO;
    }

    @Override
    @Transactional
    public void addMeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);
        setmealMapper.addMeal(setmeal);

        Long id = setmeal.getId();

        List<SetmealDish> setmealDish = new ArrayList<>();
        setmealDish = setmealDTO.getSetmealDishes();
        if (setmealDish != null && setmealDish.size() > 0) {
            setmealDish.forEach((meal) -> {
                meal.setSetmealId(id);
                setmealDishMapper.insertBatch(meal);
            });
        }
    }

    @Override
    @Transactional
    public void updateMeal(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO, setmeal);

        setmealDishMapper.deleteByMealId(setmealDTO.getId());


        List<SetmealDish> setmealDish = setmealDTO.getSetmealDishes();
        if (setmealDish != null && setmealDish.size() > 0) {
            setmealDish.forEach((meal) -> {
                meal.setId(null);
                meal.setSetmealId(setmealDTO.getId());
                setmealDishMapper.insertBatch(meal);
            });
        }
        setmealMapper.updateMeal(setmeal);

    }

    @Override
    @Transactional
    public void batchDelMeal(List<Long> ids) {

        ids.forEach((id) -> {
            SetmealVO setmealVo = setmealMapper.getMealById(id);
            if (setmealVo.getStatus() == StatusConstant.ENABLE) {
                throw new DeletionNotAllowedException(MessageConstant.SETMEAL_ON_SALE);
            }
        });

        ids.forEach((id)->{
            setmealMapper.delMealById(id);
            setmealDishMapper.deleteByMealId(id);
        });
    }

    public List<Setmeal> list(Setmeal setmeal) {
        List<Setmeal> list = setmealMapper.list(setmeal);
        return list;
    }


    public List<DishItemVO> getDishItemById(Long id) {
        return setmealMapper.getDishItemBySetmealId(id);
    }

}
