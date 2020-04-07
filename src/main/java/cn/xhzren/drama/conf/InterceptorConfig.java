package cn.xhzren.drama.conf;

import cn.xhzren.drama.interceptor.AuthInterceptor;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.nio.charset.Charset;
import java.util.List;

@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private AuthInterceptor authInterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
                .excludePathPatterns("/account/registered", "/account/accountLogin", "/account/tmpVerifyCode");
        super.addInterceptors(registry);
    }

    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter = new StringHttpMessageConverter(
                Charset.forName("UTF-8"));
        return converter;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
        converters.add(responseBodyConverter());
    }

}
