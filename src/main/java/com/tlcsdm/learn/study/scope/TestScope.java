package com.tlcsdm.learn.study.scope;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 唐 亮
 * @date: 2022/1/23 21:54
 * @since: 1.0
 */
@RestController
@RequestMapping(value = "/testScope")
public class TestScope {

    private String name;

    //分别发送请求http://localhost:8080/learn/testScope/aaa和http://localhost:8080/learn/testScope/bbb
//    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
//    public void userProfile(@PathVariable("username") String username) {
//        name = username;
//        Order order = SpringContextUtils.getBean(Order.class);
//        order.setOrderNum(name);
//        try {
//            for (int i = 0; i < 100; i++) {
//                System.out.println(
//                        Thread.currentThread().getId()
//                                + "name:" + name
//                                + "--order:"
//                                + order.getOrderNum());
//                Thread.sleep(2000);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return;
//    }

    @Autowired
    private Order order;

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public void userProfile(@PathVariable("username") String username) {
        name = username;
        order.setOrderNum(name);
        try {
            for (int i = 0; i < 100; i++) {
                System.out.println(
                        Thread.currentThread().getId()
                                + "name:" + name
                                + "--order:"
                                + order.getOrderNum());
                Thread.sleep(2000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return;
    }
}
