package com.sun.spring;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfigurationBean2 {

    private String id;
    private String name;

}
