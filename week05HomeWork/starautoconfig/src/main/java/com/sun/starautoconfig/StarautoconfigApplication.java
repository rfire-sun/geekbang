package com.sun.starautoconfig;

import com.sun.starautoconfig.entity.Klass;
import com.sun.starautoconfig.entity.Student;
import com.sun.starautoconfig.intf.ISchool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * 自认为的理想状况
 * 定义starter，就是定义一个给spring的组件,相当于给一个jar，知识starter层次更高一些
 * spring.provider定义名字，但是貌似那个好像更少一些
 * spring.factories定义加载的configuration 当使用这个starter的时候加载的东西
 * 还是容器，不过貌似不需要我们自己定义了
 * 展开来讲貌似还挺多东西的，然后就是，我理解就是提供了一套某个层次的工具箱
 */
@SpringBootApplication
public class StarautoconfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(StarautoconfigApplication.class, args);
    }


//    // ==== 测试自动配置 ====
//    @Autowired
//    WebInfo info;
//
//    @Bean
//    public void printInfo(){
//        System.out.println(info.getName());
//    }
    // ==== 测试自动配置 ====
    @Autowired
    ISchool school;

    @Autowired
    Klass klass;

    @Autowired
    Student student;

    @Bean
    public void printInfo(){

        System.out.println();

        school.ding();

        klass.dong();

        System.out.println(student);

        System.out.println();

    }

}
