package com.tlcsdm.learn.study.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 唐 亮
 * @date: 2022/2/2 0:29
 * @since: 1.0
 */
@Slf4j
@AllArgsConstructor
@RestController
public class OrderController {
    private final OrderOperatorEngine orderOperatorEngine;
    private final ObjectMapper objectMapper;

    @GetMapping("update/{orderId}/{orderStatus}")
    public void accept(@PathVariable(value = "orderId") Long orderId, @PathVariable(value = "orderStatus") Integer orderStatus) {
        OrderInfo orderInfo = OrderInfo.builder()
                .orderId(orderId)
                .orderStatus(orderStatus)
                .build();
        try {
            log.info("updateOrder info:{}", objectMapper.writeValueAsString(orderInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        orderOperatorEngine.operate(orderInfo);
    }

}
