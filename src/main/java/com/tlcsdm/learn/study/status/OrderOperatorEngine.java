package com.tlcsdm.learn.study.status;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * 状态机引擎处理类定义
 *
 * @author: 唐 亮
 * @date: 2022/2/2 0:25
 * @since: 1.0
 */
@AllArgsConstructor
@Component
public class OrderOperatorEngine {

    private final StatusProcessRegistry statusProcessRegistry;

    public boolean operate(OrderInfo orderInfo) {
        //获取当前事件处理器
        StatusProcessor statusProcessor = statusProcessRegistry.acquireStatusProcessor(orderInfo.getOrderStatus());
        if (statusProcessor == null) {
            throw new RuntimeException("NOT_FOUND_PROCESSOR");
        }
        //执行处理逻辑
        return statusProcessor.action(orderInfo);
    }

}
