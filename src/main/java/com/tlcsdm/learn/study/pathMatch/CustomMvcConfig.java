package com.tlcsdm.learn.study.pathMatch;

import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 没起作用
 *
 * @author: 唐 亮
 * @date: 2022/1/23 17:38
 * @since: 1.0
 */
//@Configuration
public class CustomMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUrlPathHelper(new CustomUrlPathHelper());
    }
}
