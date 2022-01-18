package com.tlcsdm.learn.study.zerenlian.filter;

import com.tlcsdm.learn.study.zerenlian.handler.AbstractHandler;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 黑名单校验对象
 *
 * @author: 唐 亮
 * @date: 2022/1/18 22:29
 * @since: 1.0
 */
@Component
@Order(3) //校验顺序排第3
public class CheckBlackFilterObject extends AbstractHandler {

    @Override
    public boolean doFilter(HttpServletRequest request, HttpServletResponse response) {
        //invoke black list check
        System.out.println("校验黑名单");
        return true;
    }
}
