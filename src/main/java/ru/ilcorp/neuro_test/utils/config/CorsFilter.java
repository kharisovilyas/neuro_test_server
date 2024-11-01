package ru.ilcorp.neuro_test.utils.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;

@Component
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000"); // Разрешаем доступ с Nuxt.js
        response.setHeader("Access-Control-Allow-Origin", "194.58.114.242:8080"); // Разрешаем доступ с Nuxt.js
        response.setHeader("Access-Control-Allow-Origin", "https://ml-edu-platform.netlify.app/"); // Разрешаем доступ с Nuxt.js
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization, Accept, Origin, X-Requested-With");

        chain.doFilter(req, res);
    }
}
