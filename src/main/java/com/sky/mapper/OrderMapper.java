package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersConfirmDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.aspectj.weaver.ast.Or;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * ClassName: OrderMapper
 * Package: com.sky.mapper
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/20 13:04
 * @Version 1.0
 */
@Mapper
public interface OrderMapper {

    void insert(Orders orders);

    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);


    void update(Orders orders);

    @Select("select * from orders where id = #{id}")
    Orders getOrderById(Long id);

    Page<Orders> page(OrdersPageQueryDTO ordersPageQueryDTO);

    @Select("select count(*) from orders where status = #{status}")
    Integer statistics(Integer status);

    @Update("update orders set status = #{status} where id = #{id}")
    void updateToConfirm(OrdersConfirmDTO ordersConfirmDTO);
    @Select("select * from orders where status = #{status} and order_time < #{orderTime}")
    List<Orders> getByStatusAndOrderTime(Integer status, LocalDateTime orderTime);

    Double sumByMap(Map map);


    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin,LocalDateTime end);
    Integer countByMap(Map map);
}
