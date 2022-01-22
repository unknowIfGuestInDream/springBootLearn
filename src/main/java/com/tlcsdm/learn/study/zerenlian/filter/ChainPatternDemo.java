package com.tlcsdm.learn.study.zerenlian.filter;

import com.tlcsdm.learn.study.zerenlian.handler.AbstractHandler;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 责任链
 *
 * @author: 唐 亮
 * @date: 2022/1/18 22:31
 * @since: 1.0
 */
@Component("ChainPatternDemo")
public class ChainPatternDemo implements InitializingBean {

    //自动注入各个责任链的对象
    @Autowired
    private List<AbstractHandler> abstractHandleList;

    private AbstractHandler abstractHandler;

    //直接调用这个方法使用
    public HttpServletResponse exec(HttpServletRequest request, HttpServletResponse response) {
        abstractHandler.filter(request, response);
        return response;
    }

    public AbstractHandler getAbstractHandler() {
        return abstractHandler;
    }

    public void setAbstractHandler(AbstractHandler abstractHandler) {
        this.abstractHandler = abstractHandler;
    }

    @Override
    public void afterPropertiesSet() {
        //spring注入后自动执行，责任链的对象连接起来
        for (int i = 0; i < abstractHandleList.size(); i++) {
            if (i == 0) {
                abstractHandler = abstractHandleList.get(0);
            } else {
                AbstractHandler currentHander = abstractHandleList.get(i - 1);
                AbstractHandler nextHander = abstractHandleList.get(i);
                currentHander.setNextHandler(nextHander);
            }
        }
    }
}
