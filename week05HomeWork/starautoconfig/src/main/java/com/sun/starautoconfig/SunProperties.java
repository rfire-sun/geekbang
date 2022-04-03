package com.sun.starautoconfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "spring.sun")
@Getter
@Setter
public class SunProperties {

    private String a = "aaa";
    private String b = "bbb";
    private String c = "ccc";
    private String d = "ddd";
    private String e = "eee";
    private String f = "fff";
    private String g = "ggg";
    private String h = "hhh";

//    private Properties props = new Properties();


}
