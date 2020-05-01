package com.simple.manage.config;

import feign.codec.Decoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Description feign 配置
 * Author chen
 * CreateTime 2020-05-01 9:57
 **/
@Configuration
public class FeignConfig {
    /**
     * 升级版本后， 不加这个 gateway 使用feign会报错，同时feign client返回对象中泛型必须要有无参构造器，不知道什么原因
     *
     * @return
     */
    @Bean
    public Decoder feignFormDecoder() {
        List<HttpMessageConverter<?>> converters = new RestTemplate().getMessageConverters();
        ObjectFactory<HttpMessageConverters> factory = () -> new HttpMessageConverters(converters);
        return new ResponseEntityDecoder(new SpringDecoder(factory));
    }
}