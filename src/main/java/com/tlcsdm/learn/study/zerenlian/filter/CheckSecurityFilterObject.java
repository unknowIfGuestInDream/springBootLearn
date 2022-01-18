package com.tlcsdm.learn.study.zerenlian.filter;

import com.tlcsdm.learn.study.zerenlian.handler.AbstractHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 安全校验对象
 *
 * @author: 唐 亮
 * @date: 2022/1/18 22:30
 * @since: 1.0
 */
@Component
@Order(2) //校验顺序排第2
public class CheckSecurityFilterObject extends AbstractHandler {

    @Override
    public boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
        //invoke Security check
        System.out.println("安全调用校验");
        return true;
    }
}
