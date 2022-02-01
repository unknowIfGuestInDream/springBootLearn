package com.tlcsdm.learn.study.status;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: 唐 亮
 * @date: 2022/2/2 0:15
 * @since: 1.0
 */
@Slf4j
@OrderStatusProcessor(status = OrderStatusEnum.COMPLETE)
public class StatusCompleteProcessor implements StatusProcessor {
    @Override
    public boolean action(OrderInfo orderInfo) {
        log.info("订单==>编号: ({}),执行({})操作", orderInfo.getOrderId(), OrderStatusEnum.getEnumByCode(orderInfo.getOrderStatus()));
        return false;
    }
}
