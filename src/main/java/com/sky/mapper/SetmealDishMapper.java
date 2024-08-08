package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ClassName: SetmealDishMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/5 20:21
 * @Version 1.0
 */
@Mapper
public interface SetmealDishMapper {

    List<Long> getSetmealIdsByDishIDs(List<Long> dishIds);

    @Select("select * from setmeal_dish where setmeal_id = #{id}")
    List<SetmealDish> getSetmealDishBySetmealId(Long id);

    @Insert("insert into setmeal_dish (setmeal_id, dish_id, name, price, copies, category_id)VALUES (#{setmealId},#{dishId},#{name},#{price},#{copies},#{categoryId})")
    void insertBatch(SetmealDish meal);

    @Delete("delete from setmeal_dish where setmeal_id = #{mealId}")
    void deleteByMealId(Long mealId);
}
