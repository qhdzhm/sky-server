package com.sky.controller.admin;

import com.github.pagehelper.Page;
import com.sky.dto.SetmealDTO;
import com.sky.dto.SetmealPageQueryDTO;
import com.sky.entity.Setmeal;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.SetmealService;
import com.sky.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: SetmealController
 * Package: com.sky.controller.admin
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/6 14:05
 * @Version 1.0
 */
@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping("/admin/setmeal")
public class SetmealController {
    @Autowired
    private SetmealService setmealService;
    @GetMapping("/page")
    public Result<PageResult> getMealList(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("pageinfo:{}",setmealPageQueryDTO);
        PageResult page = setmealService.getMeal(setmealPageQueryDTO);

        return Result.success(page);
    }

    @GetMapping("/{id}")
    public Result<SetmealVO> getMealById(@PathVariable Long id){
        log.info("Id:{}",id);
        SetmealVO setmealVO = setmealService.getMealById(id);

        return Result.success(setmealVO);
    }

    @PostMapping
    @CacheEvict(cacheNames = "setmealCache",key = "#setmealDTO.categoryId")
    public Result addMeal(@RequestBody SetmealDTO setmealDTO){
        log.info("add meal:{}",setmealDTO);
        setmealService.addMeal(setmealDTO);
        return Result.success();
    }

    @PutMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result updateMeal(@RequestBody SetmealDTO setmealDTO){
        log.info("updatemeal:{}",setmealDTO);
        setmealService.updateMeal(setmealDTO);
        return Result.success();
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "setmealCache",allEntries = true)
    public Result batchDelMeal(@RequestParam List<Long> ids){
        log.info("deleteIds:{}",ids);
        setmealService.batchDelMeal(ids);
        return Result.success();
    }
}
