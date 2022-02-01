package com.tlcsdm.learn.study.status;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义处理器实现，并用自定义注解标识
 *
 * @author: 唐 亮
 * @date: 2022/2/2 0:12
 * @since: 1.0
 */
@Slf4j
@OrderStatusProcessor(status = OrderStatusEnum.CREATE)
public class StatusCreateProcessor implements StatusProcessor {
    @Override
    public boolean action(OrderInfo orderInfo) {
        log.info("订单==>编号: ({}),执行({})操作", orderInfo.getOrderId(), OrderStatusEnum.getEnumByCode(orderInfo.getOrderStatus()));
        return false;
    }
}
