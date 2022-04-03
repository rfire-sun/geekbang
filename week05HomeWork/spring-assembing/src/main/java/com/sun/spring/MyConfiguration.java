package com.sun.spring;

import com.sun.spring.ConfigurationBean1;
import com.sun.spring.ConfigurationBean2;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MyConfiguration {


    @Bean
    public ConfigurationBean1 configurationBean1() {

        return new ConfigurationBean1().setId("nihao").setName("haohao");
    }

    @Bean
    public ConfigurationBean2 configurationBean2() {
        return new ConfigurationBean2().setId("huohuo").setName("hehe");

    }
}
