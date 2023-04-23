package br.com.bankeng.transactionbff.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;


@Configuration
public class FeignConfig {

    @Bean
    public HttpMessageConverters messageConverter() {
        return new HttpMessageConverters(new MarshallingHttpMessageConverter());
    }
}

