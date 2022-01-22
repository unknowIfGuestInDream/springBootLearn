package com.tlcsdm.learn.study.zerenlian;

import com.tlcsdm.learn.study.zerenlian.filter.ChainPatternDemo;
import com.tlcsdm.learn.study.zerenlian.handler.AbstractHandler;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: 唐 亮
 * @date: 2022/1/18 22:32
 * @since: 1.0
 */
@RestController
@RequestMapping("/superadmin")
@AllArgsConstructor
public class AreaController {
    private final ChainPatternDemo chainPatternDemo;
    private final Map<String, AbstractHandler> abstractHandlerMap;

    @GetMapping(value = "/listarea")
    public Map<String, Object> listArea(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> modelMap = new HashMap<>();
        System.out.println("---" + chainPatternDemo.exec(request, response));
        System.out.println(abstractHandlerMap);
        return modelMap;
    }

}
