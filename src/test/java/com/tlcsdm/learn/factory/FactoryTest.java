package com.tlcsdm.learn.factory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: 唐 亮
 * @date: 2022/1/16 20:16
 * @since: 1.0
 */
@SpringBootTest
public class FactoryTest {
    @Autowired
    FactoryForStrategy factoryForStrategy;

    @Test
    public void test1() {
        factoryForStrategy.getStrategy("twoStrategy").doOper();
    }

}
