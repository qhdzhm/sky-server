package com.sky.service;

import com.sky.dto.*;
import com.sky.result.PageResult;
import com.sky.vo.OrderPaymentVO;
import com.sky.vo.OrderStatisticsVO;
import com.sky.vo.OrderSubmitVO;
import com.sky.vo.OrderVO;

/**
 * ClassName: OrderService
 * Package: com.sky.service
 * Description:
 *
 * @Author Tangshifu
 * @Create 2024/7/20 13:01
 * @Version 1.0
 */
public interface OrderService {

    OrderSubmitVO submitOrder(OrdersSubmitDTO ordersSubmitDTO);

    /**
     *
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO) throws Exception;

    /**
     *
     * @param outTradeNo
     */
    void paySuccess(String outTradeNo);

    OrderVO getOrderById(Long id);

    PageResult getHistoryOrder(int page, int pageSize, Integer status);

    void cancelOrder(Long id);

    void repetition(Long id);

    PageResult page(OrdersPageQueryDTO ordersPageQueryDTO);

    OrderStatisticsVO statistics();

    void updateToConfirm(OrdersConfirmDTO ordersConfirmDTO);
    void updateToRejection(OrdersRejectionDTO ordersRejectionDTO);

    void updateToCancel(OrdersCancelDTO ordersCancelDTO);

    void delivery(Long id);

    void complete(Long id);

    void reminder(Long id);
}
