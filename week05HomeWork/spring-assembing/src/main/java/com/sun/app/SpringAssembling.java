package com.sun.app;

import com.sun.spring.SunService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Arrays;

public class SpringAssembling {

    public static void main(String[] args) {

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");


        System.out.println(context.getBean("student1"));
        System.out.println(context.getBean("student2"));
        System.out.println(context.getBean("class1"));


        SunService sunService = context.getBean(SunService.class);
        sunService.doService();


        System.out.println(Arrays.toString(context.getBeanDefinitionNames()));

        System.out.println(context.getBean("configurationBean1"));
        System.out.println(context.getBean("configurationBean2"));

    }
}
