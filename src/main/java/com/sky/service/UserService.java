package com.sky.service;

import com.sky.dto.UserLoginDTO;
import com.sky.entity.User;
import com.sky.vo.UserLoginVO;

/**
 * ClassName: UserService
 * Package: com.sky.service
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/15 15:26
 * @Version 1.0
 */
public interface UserService {
    User wxLogin(UserLoginDTO userLoginDTO);
}
