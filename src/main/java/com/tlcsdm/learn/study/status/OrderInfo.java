package com.tlcsdm.learn.study.status;

import lombok.Builder;
import lombok.Data;

/**
 * @author: 唐 亮
 * @date: 2022/2/2 0:10
 * @since: 1.0
 */
@Data
@Builder
public class OrderInfo {

    /**
     * 订单编号
     */
    private Long orderId;

    /**
     * 订单状态 1-新建；2-完成；3-取消
     */
    private Integer orderStatus;

}
