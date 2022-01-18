package com.tlcsdm.learn.study.zerenlian.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 唐 亮
 * @date: 2022/1/18 22:28
 * @since: 1.0
 */
public abstract class AbstractHandler {

    //责任链中的下一个对象
    private AbstractHandler nextHandler;

    /**
     * 责任链的下一个对象
     */
    public void setNextHandler(AbstractHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    /**
     * 具体参数拦截逻辑,给子类去实现
     */
    public void filter(HttpServletRequest request, HttpServletResponse response) {
        if (doFilter(request, response)) {
            if (getNextHandler() != null) {
                getNextHandler().filter(request, response);
            }
        }
    }

    public AbstractHandler getNextHandler() {
        return nextHandler;
    }

    public abstract boolean doFilter(HttpServletRequest request, HttpServletResponse response);

}
