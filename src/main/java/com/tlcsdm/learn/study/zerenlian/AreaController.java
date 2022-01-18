package com.tlcsdm.learn.study.zerenlian;

import com.tlcsdm.learn.study.zerenlian.filter.ChainPatternDemo;
import org.springframework.beans.factory.annotation.Autowired;
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
public class AreaController {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private ChainPatternDemo chainPatternDemo;

    @GetMapping(value = "/listarea")
    public Map<String, Object> listArea() {
        Map<String, Object> modelMap = new HashMap<>();
        System.out.println("---" + chainPatternDemo.exec(request, response));
        return modelMap;
    }

}
