package com.tlcsdm.learn.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: 唐 亮
 * @date: 2022/1/16 20:17
 * @since: 1.0
 */
@Service
public class FactoryForStrategy {

    @Autowired
    public Map<String, Strategy> strategys = new ConcurrentHashMap<>();

    public Strategy getStrategy(String component) {
        Strategy strategy = strategys.get(component);
        if (component == null) {
            throw new RuntimeException("no strategy defined");
        }
        return strategy;
    }
}
