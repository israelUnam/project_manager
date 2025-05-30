package com.losung.projectmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
                if (modelAndView != null) {
                    String path = request.getRequestURI();
                    // Extraer la ruta base (sin parámetros ni IDs)
                    if (path.matches("/.*/\\d+.*")) {
                        path = path.replaceAll("/\\d+.*", "");
                    }
                    modelAndView.addObject("currentPath", path);
                }
            }
        });
    }
} 