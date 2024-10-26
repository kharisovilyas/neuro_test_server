package ru.ilcorp.neuro_test.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Разрешаем доступ ко всем маршрутам
                .allowedOrigins("*") // Разрешаем запросы с этого источника
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешаем необходимые методы
                .allowedHeaders("*") // Разрешаем все заголовки
                .allowCredentials(true); // Если нужны куки или авторизационные заголовки
    }
}