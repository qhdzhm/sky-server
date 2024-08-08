package com.sky.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sky.entity.Orders;
import com.sky.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * ClassName: MyTask
 * Package: com.sky.task
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/21 14:29
 * @Version 1.0
 */

@Component

public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 * * * * ? ")
    public void processTimeoutOrder() {
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(Orders.PENDING_PAYMENT, LocalDateTime.now().minusMinutes(15));
        if(ordersList!=null && ordersList.size()>0){
            ordersList.forEach(order->{
                order.setStatus(Orders.CANCELLED);
                order.setCancelReason("Time out");
                order.setCancelTime(LocalDateTime.now());

                orderMapper.update(order);
            });
        }
    }

    @Scheduled(cron = "0 0 1 * * ? ")
    public void processDeliveryOrder() {
        List<Orders> ordersList = orderMapper.getByStatusAndOrderTime(Orders.DELIVERY_IN_PROGRESS, LocalDateTime.now().minusMinutes(60));
        if(ordersList!=null && ordersList.size()>0){
            ordersList.forEach(order->{
                order.setStatus(Orders.COMPLETED);
                orderMapper.update(order);
            });
        }
    }
}
