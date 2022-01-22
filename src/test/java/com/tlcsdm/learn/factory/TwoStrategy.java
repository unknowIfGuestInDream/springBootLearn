package com.tlcsdm.learn.factory;

import org.springframework.stereotype.Component;

/**
 * @author: 唐 亮
 * @date: 2022/1/16 20:21
 * @since: 1.0
 */
@Component("twoStrategy")
public class TwoStrategy implements Strategy {
    @Override
    public void doOper() {
        System.out.println("two");
    }
}
