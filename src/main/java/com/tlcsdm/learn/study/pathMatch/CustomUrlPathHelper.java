package com.tlcsdm.learn.study.pathMatch;

import org.springframework.web.util.UrlPathHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: 唐 亮
 * @date: 2022/1/23 10:31
 * @since: 1.0
 */
public class CustomUrlPathHelper extends UrlPathHelper {

    @Override
    public String getLookupPathForRequest(HttpServletRequest request) {
        String url = super.getLookupPathForRequest(request);
        System.out.println(url);
        if (url.startsWith("/test")) {
            String substring = url.substring(5);
            return substring;
        }
        return url;
    }
}
