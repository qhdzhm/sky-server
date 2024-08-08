package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import com.sky.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * ClassName: DishController
 * Package: com.sky.controller.admin
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/1 14:30
 * @Version 1.0
 */
@RestController
@Slf4j
@RequestMapping("/admin/dish")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class DishController {
    @Autowired
    private DishService dishService;
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/page")
    public Result<PageResult> getDishList(DishPageQueryDTO dishPageQueryDTO){
        PageResult pageResult =dishService.getDishList(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result addDish(@RequestBody DishDTO dishDTO){
        log.info("dishDto:{}",dishDTO);
        dishService.addDishWithFlavour(dishDTO);
        String key = "dish_" + dishDTO.getCategoryId();

        redisTemplate.delete(key);
        return Result.success();
    }


    @GetMapping("/{id}")
    public Result<DishVO> getDishById(@PathVariable Long id){
        log.info("id : {}",id);
        DishVO dishVO = dishService.getDishById(id);
        return Result.success(dishVO);
    }

    @DeleteMapping
    public Result batchDel(@RequestParam List<Long> ids){
        log.info("deleteIds:{}",ids);
        dishService.batchDel(ids);
        cleanCache("dish_*");
        return Result.success();
    }

    @PutMapping
    public Result updateDish(@RequestBody DishDTO dishDTO){
        log.info("dishDTO:{}",dishDTO);
        dishService.updateDish(dishDTO);
        cleanCache("dish_*");
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Dish>> getDishByCategoryId(Integer categoryId){
        log.info("category id : {}",categoryId);
        List<Dish> dish = dishService.getDishByCategoryId(categoryId);

        return Result.success(dish);
    }

    private void cleanCache(String pattern){
        Set keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
}
