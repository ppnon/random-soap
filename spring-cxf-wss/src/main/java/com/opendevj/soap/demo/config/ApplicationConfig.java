package com.opendevj.soap.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.opendevj.soap.demo.interceptor.LoggingRequestFilter;
import com.opendevj.soap.demo.security.DefaultJsonDeserializer;

import brave.Tracer;

@Configurable
public class ApplicationConfig extends WebMvcConfigurationSupport {

	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource
          = new ReloadableResourceBundleMessageSource();
         
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
    @Bean
    public FilterRegistrationBean<LoggingRequestFilter> loggingFilterRegistrationBean(Tracer tracer) {
    	FilterRegistrationBean<LoggingRequestFilter> filterBean = new FilterRegistrationBean<>();
    	filterBean.setName("LogginFilter");
    	filterBean.setFilter(new LoggingRequestFilter(tracer));
    	filterBean.setOrder(1);
    	return filterBean;
    }
    
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(jsonConverter());
    }

    @Bean
    public HttpMessageConverter<Object> jsonConverter() {
        SimpleModule module = new SimpleModule();
        module.addDeserializer(String.class, new DefaultJsonDeserializer());
        ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();
        objectMapper.registerModule(module);
        return new MappingJackson2HttpMessageConverter(objectMapper);
    }
}
