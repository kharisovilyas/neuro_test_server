package ru.ilcorp.neuro_test.utils.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**") // Укажите путь, который хотите настроить
                .allowedOrigins("http://82.179.36.248") // Укажите разрешенные источники
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Укажите разрешенные методы
                .allowedHeaders("*") // Разрешите все заголовки
                .allowCredentials(true); // Если нужно использовать куки
    }
}
