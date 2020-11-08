package com.seven.cola.support;

import com.alibaba.cola.dto.RequestContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author carter
 * create_date  2020/6/15 19:15
 * description     拦截器，设置通用参数，设置跨域头
 */
@Slf4j
public class RequestContextInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        RequestContext requestContext = RequestContext.get();
        requestContext.setRequestTime(System.currentTimeMillis());
//        String requestId = this.getStringValue(request, "requestId")
//                .orElseThrow(() -> new IllegalArgumentException("requestId缺失"));
//        requestContext.setRequestId(requestId);


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
                .filter(StringUtils::isNotBlank)
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
                .filter(StringUtils::isNotBlank)
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
                .filter(StringUtils::isNumeric)
                .map(Long::parseLong)
                .filter(item -> item >= 0)
                ;
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
