package com.tlcsdm.learn.study.zerenlian.filter;

import com.tlcsdm.learn.study.zerenlian.handler.AbstractHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 规则拦截对象
 *
 * @author: 唐 亮
 * @date: 2022/1/18 22:30
 * @since: 1.0
 */
@Component
@Order(4) //校验顺序排第4
public class CheckRuleFilterObject extends AbstractHandler {

    @Override
    public boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
        //check rule
        System.out.println("check rule");
        return true;
    }
}
