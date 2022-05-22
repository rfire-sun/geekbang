package com.sun.starautoconfig;

import com.google.common.collect.Lists;
import com.sun.starautoconfig.entity.Klass;
import com.sun.starautoconfig.entity.School;
import com.sun.starautoconfig.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(SunConfiguration.class)
@EnableConfigurationProperties(SunProperties.class)
public class SunAutoConfiguration {

    @Autowired
    SunProperties properties;

    @Autowired
    SunConfiguration configuration;

    @Bean
    public Student student() {
        return new Student().setName(properties.getA()).setId(12);
    }

    @Bean
    public Student student100() {
        return new Student().setName(properties.getB()).setId(12);
    }

    @Bean
    public Klass klass() {

        Klass klass = new Klass();
        klass.setStudents(Lists.newArrayList(new Student().setName(configuration.name1 + properties.getC())));
        return klass;
    }

    @Bean
    public School school() {
        return new School();
    }

}
