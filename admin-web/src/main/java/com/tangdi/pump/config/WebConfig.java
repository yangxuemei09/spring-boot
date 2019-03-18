package com.tangdi.pump.config;

import com.tangdi.pump.interceptor.TokenInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author ron
 * @date 2018/12/28 10:50
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    private final String[] excludePatterns = new String[]{"/login"};

    @Bean
    public HandlerInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor()).excludePathPatterns(excludePatterns);
        super.addInterceptors(registry);
    }

}
