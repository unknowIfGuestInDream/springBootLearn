package com.tlcsdm.learn.study.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum OrderStatusEnum {
    CREATE(1, "新建"),
    COMPLETE(2, "完成"),
    CANCLE(3, "取消");

    private final Integer statusCode;
    private final String statusDesc;

    public static OrderStatusEnum getEnumByCode(Integer statusCode) {
        if (statusCode == null) {
            return null;
        }
        return Arrays.stream(values()).filter(e -> e.getStatusCode().equals(statusCode)).findFirst().get();
    }

}
