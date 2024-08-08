package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.enumeration.OperationType;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * ClassName: CategoryMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/6/30 14:54
 * @Version 1.0
 */
@Mapper
public interface CategoryMapper {


    Page<Category> getCategory(CategoryPageQueryDTO categoryPageQueryDTO);

    @Insert("insert into category (type,name,sort,status,create_time,update_time,create_user,update_user) values (#{type},#{name},#{sort},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void addCategory(Category category);


    @AutoFill(value = OperationType.UPDATE)
    void updateCategory(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteCategory(Long id);

    List<Category> getCategoryBytype(Integer type);
}
