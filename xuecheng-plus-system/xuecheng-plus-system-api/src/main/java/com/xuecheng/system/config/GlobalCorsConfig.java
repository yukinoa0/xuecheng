package com.xuecheng.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Author cliced
 * @Version 1.0
 * @Description 跨域访问配置类
 * @Date 2025/01/11 00:19
 */
@Configuration
public class GlobalCorsConfig {
    /**
     * 跨域访问过滤器
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // 允许白名单域名进行跨域访问
        config.addAllowedOriginPattern("*");
        // 允许跨域发送 cookie
        config.setAllowCredentials(true);
        // 放行全部原始请求头的信息
        config.addAllowedHeader("*");
        // 允许所有请求方法跨域访问
        config.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
