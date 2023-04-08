package com.alibaba.cola.adapter.ext.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Describe: 用于定义基本拦截的基本类
 * @author qi.wei
 */
public class RequestContextInterceptor implements HandlerInterceptor {

    public Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        RequestContext requestContext = RequestContext.get();
        requestContext.setRequestTime(System.currentTimeMillis());
        String requestId = this.getStringValue(request, "requestId")
                .orElse("-1");
        requestContext.setRequestId(requestId);

        getStringValue(request, "country").ifPresent(requestContext::setCountry);
        getStringValue(request, "city").ifPresent(requestContext::setCity);
        getStringValue(request, "province").ifPresent(requestContext::setProvince);
        getStringValue(request, "area").ifPresent(requestContext::setArea);
        getIntegerValue(request, "osType").ifPresent(requestContext::setOsType);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        Map<String, String> headMap = new HashMap<>(4);
        headMap.put("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        headMap.put("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type");
        headMap.put("Access-Control-Allow-Credentials", "true");
        headMap.put("Access-Control-Allow-Origin", "*");
        Arrays.asList(request.getHeader("Referer"), request.getHeader("Origin"))
                .stream()
                .filter(StringUtils::hasText)
                .findFirst()
                .ifPresent(item -> headMap.put("Access-Control-Allow-Origin", item));
        headMap.forEach((key, value) -> response.addHeader(key, value));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        RequestContext requestContext = RequestContext.get();

        log.debug("requestId:{}\nuseTime:{}",
                requestContext.getRequestId(),
                Math.subtractExact(System.currentTimeMillis(), requestContext.getRequestTime()));

        //清理上下文对象
        RequestContext.clear();

    }

    /**
     * 从请求头或者参数中获取字符类型的参数
     *
     * @param request   请求对象
     * @param paramName 参数名
     * @return 字符串容器，可能有，也可能没有；
     */
    private Optional<String> getStringValue(HttpServletRequest request, String paramName) {

        String paramValue = Optional.ofNullable(request.getHeader(paramName))
                .filter(StringUtils::hasText)
                .orElse(request.getParameter(paramName));

        return Optional.ofNullable(paramValue);
    }

    /**
     * 从请求头或者url参数中获取参数
     *
     * @param request   请求对象
     * @param paramName 参数名
     * @return 整型容器，可能有，也可能没有
     */
    private Optional<Long> getLongValue(HttpServletRequest request, String paramName) {

        return getStringValue(request, paramName)
                .filter(this::isNumeric)
                .map(Long::parseLong)
                .filter(item -> item >= 0);
    }


    public boolean isNumeric(String str){
        for(int i=str.length();--i>=0;){
            int chr=str.charAt(i);
            if(chr<48 || chr>57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从请求头或者url中获取参数
     *
     * @param request   请求对象
     * @param paramName 参数名
     * @return 可能有，也可能没有
     */
    private Optional<Integer> getIntegerValue(HttpServletRequest request, String paramName) {
        return getLongValue(request, paramName).map(Long::intValue).filter(item -> item >= 0);
    }
}
