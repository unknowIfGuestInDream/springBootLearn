package com.tlcsdm.learn.study.zerenlian.filter;

import com.tlcsdm.learn.study.zerenlian.handler.AbstractHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 参数校验对象
 *
 * @author: 唐 亮
 * @date: 2022/1/18 22:30
 * @since: 1.0
 */
@Component
@Order(1) //顺序排第1，最先校验
public class CheckParamFilterObject extends AbstractHandler {

    @Override
    public boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("非空参数检查");
        return true;
    }
}
