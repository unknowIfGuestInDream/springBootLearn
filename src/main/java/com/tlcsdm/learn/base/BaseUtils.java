package com.tlcsdm.learn.base;

import com.alibaba.druid.util.StringUtils;
import lombok.Cleanup;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author: TangLiang
 * @date: 2021/4/14 15:16
 * @since: 1.0
 */
public class BaseUtils {

    /**
     * 最大上传文件大小
     */
    public static long MAX_UPLOAD_SIZE = 1048576 * 30;

    /**
     * 类不能实例化
     */
    private BaseUtils() {
    }

    /**
     * 不同浏览器将下载文件名处理为中文
     *
     * @param request HttpServletRequest对象
     * @param s       文件名
     * @return 文件名
     * @throws UnsupportedEncodingException 不支持的解码方式
     */
    public static String getFormatString(HttpServletRequest request, String s) throws UnsupportedEncodingException {
        String filename = s;
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        if (userAgent.indexOf("FIREFOX") > 0) {
            filename = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1); // firefox浏览器
        } else if (userAgent.indexOf("MSIE") > 0) {
            filename = URLEncoder.encode(s, "UTF-8");// IE浏览器
        } else if (userAgent.indexOf("CHROME") > 0) {
            filename = new String(s.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);// 谷歌
        }
        return filename;
    }

    /**
     * 下载文件
     *
     * @param inputStream 文件输入流
     * @param fileName    文件名
     * @param request     request
     * @param response    response
     */
    public static void download(InputStream inputStream, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        fileName = getFormatString(request, fileName);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);// 下载模式
        @Cleanup ServletOutputStream out = response.getOutputStream();
        byte[] content = new byte[65535];
        int length = 0;
        while ((length = inputStream.read(content)) != -1) {
            out.write(content, 0, length);
            out.flush();
        }
    }

    /**
     * 流转字节数组
     *
     * @param inputStream 流
     * @return 流转字节数组
     * @throws Exception IOException
     */
    public static byte[] inputStreamToBytes(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] content = new byte[65535];
        int length = 0;
        while ((length = inputStream.read(content)) != -1) {
            baos.write(content, 0, length);
        }

        return baos.toByteArray();
    }

    /**
     * 流转字符串
     *
     * @param inputStream 流
     * @return 流转字符串
     * @throws Exception IOException
     */
    public static String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] content = new byte[65535];
        int length = 0;
        while ((length = inputStream.read(content)) != -1) {
            baos.write(content, 0, length);
        }

        return baos.toString();
    }

    /**
     * 深度克隆
     *
     * @param object
     * @return
     */
    public static Object deepClone(Object object) {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            if (object != null) {
                objectOutputStream.writeObject(object);
            }

            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
            return (Serializable) objectInputStream.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * transfer xml to bean
     *
     * @param xml 待转化的xml字符串
     * @param c   转化后的类
     * @param <T> 转化后类类型
     * @return 转化后的类对象
     */
    public static <T> T xmlToBean(String xml, Class<T> c) {
        T t = null;
        try {
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(new StringReader(xml));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return t;
    }

    //整理数据
    public static List<Map<String, Object>> listMapper(List<Map<String, Object>> list) {
        List data = new ArrayList();
        for (Object o : list) {
            data.add(modelMapper((Map<String, Object>) o));
        }
        return data;
    }

    //数据转换gantt
    public static Map<String, Object> modelMapper(Map<String, Object> o) {
        Map<String, Object> task = new HashMap();
        task.put("UID", o.get("V_GUID"));                        //唯一标识符
        task.put("ID", o.get("ID"));                          //序号
        task.put("Name", o.get("V_PORJECT_NAME"));//工程名称
        task.put("Start", o.get("V_BDATE"));//开工时间
        task.put("Finish", o.get("V_EDATE"));//竣工时间
        task.put("Duration", o.get("V_Duration"));//工期
        task.put("PercentComplete", o.get("V_PROGRESS"));//进度
        task.put("V_FLAG", o.get("V_FLAG"));//计划类型
        task.put("V_CONTENT", o.get("V_CONTENT"));//维修内容
        task.put("ParentTaskUID", o.get("V_GUID_UP"));//父节点

        return task;
    }

    //进度计算
    public static int differentDays(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2)   //不同一年
        {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2 - day1);
        } else    //同一年
        {
            return day2 - day1;
        }
    }

    /**
     * 个位数填充0
     *
     * @param str 需要填充的字符串
     * @return 填充后结果
     */
    public static String fillWithZero(String str) {
        if (str != null && str.length() < 2) {
            return "0" + str;
        }
        return str;
    }

    /**
     * 获得UUID
     *
     * @return UUID
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 返回成功信息(用于存储过程方式的结果返回)
     *
     * @param result 数据集
     * @return java.util.Map
     */
    public static Map<String, Object> success(Map<String, Object> result) {
        result.put("success", true);
        return result;
    }

    /**
     * 返回成功信息(用于JdbcTemplate的结果返回)
     *
     * @param list 数据集
     * @return java.util.Map
     */
    public static <T> Map<String, Object> success(List<T> list) {
        Map<String, Object> result = new HashMap<>(4);
        result.put("success", true);
        result.put("data", list);
        return result;
    }

    /**
     * 返回成功信息(用于JdbcTemplate的结果返回)
     *
     * @param list  数据集
     * @param total 数据集总数
     * @return java.util.Map
     */
    public static <T> Map<String, Object> success(List<T> list, int total) {
        Map<String, Object> result = success(list);
        result.put("total", total);
        return result;
    }

    /**
     * 返回成功信息(用于存储过程方式的结果返回)
     *
     * @return java.util.Map
     */
    public static Map<String, Object> success() {
        return Collections.singletonMap("success", true);
    }

    /**
     * 返回失败信息(用于存储过程方式的结果返回)
     *
     * @param message 错误信息
     * @return java.util.Map
     */
    public static Map<String, Object> failed(String message) {
        Map<String, Object> result = new HashMap<>(4);
        result.put("success", false);
        result.put("message", message);
        return result;
    }

    /**
     * 返回失败信息(用于存储过程方式的结果返回)
     *
     * @param result  数据集
     * @param message 错误信息
     * @return java.util.Map
     */
    public static Map<String, Object> failed(Map<String, Object> result, String message) {
        result.put("success", false);
        result.put("message", message);
        return result;
    }

    /**
     * 获取请求客户端ip地址
     *
     * @param request request
     * @return 客户端ip地址
     */
    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            return "127.0.0.1";
        }

        return ip;
    }

    /**
     * 获取请求url路径
     *
     * @param request request
     * @return url路径
     */
    public static String getUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getServletPath();
        if (request.getQueryString() != null) {
            url += "?" + request.getQueryString();
        }

        return url;
    }

    /**
     * 主机名
     *
     * @return 主机名
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "未知";
    }

    /**
     * 获取cookie值
     *
     * @param key     cookie键
     * @param request request
     * @return cookie值
     */
    public static String getCookieValue(String key, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    /**
     * 分页
     *
     * @param list  需要分页的list
     * @param page  当前页数
     * @param limit 每次展示页数
     * @return 分页后的list
     */
    public static <T> List<T> page(List<T> list, Integer page, Integer limit) {
        return page != null && limit != null && page > 0 && limit > 0 ?
                list.stream()
                        .skip(limit * (page - 1))
                        .limit(limit)
                        .collect(Collectors.toList())
                : list;
    }

    public static String valueOf(Object obj) {
        return (obj == null) ? "" : obj.toString();
    }

    /**
     * 设置cookie
     *
     * @param response HttpServletResponse
     * @param name     cookie名字
     * @param value    cookie值
     * @param maxAge   cookie生命周期  以秒为单位
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        if (maxAge > 0) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}
