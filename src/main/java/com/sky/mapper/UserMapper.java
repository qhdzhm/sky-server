package com.sky.mapper;

import com.sky.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

/**
 * ClassName: UserMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/15 16:10
 * @Version 1.0
 */
@Mapper
public interface UserMapper {

    @Select("select * from sky_take_out.user where openid = #{openid}")
    User getUserByOpenID(String openid);

    @Select("select * from sky_take_out.user where id = #{userId}")
    User getUserById(Long userId);

    void addUser(User user);
    Integer userCount(Map map);

}
