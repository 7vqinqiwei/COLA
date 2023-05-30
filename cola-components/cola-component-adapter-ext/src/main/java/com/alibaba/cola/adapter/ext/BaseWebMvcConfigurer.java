package com.alibaba.cola.adapter.ext;

import com.alibaba.cola.adapter.ext.interceptor.RequestContextInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Describe: 用于定义基本拦截的基类
 * @author qi.wei
 */
public abstract class BaseWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new RequestContextInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/echo", "/error");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 解决静态资源无法访问
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 解决swagger无法访问
        registry.addResourceHandler("/swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        // 解决swagger的js文件无法访问
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {

//        List<String> methods = new ArrayList<>();
//        methods.add(HttpMethod.GET.name());
//        HttpMethod.GET.name(),HttpMethod.POST.name(), HttpMethod.PUT.name(),
//        HttpMethod.OPTIONS.name(), HttpMethod.DELETE.name()

        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .maxAge(3600)
                .allowCredentials(true);
    }


}
