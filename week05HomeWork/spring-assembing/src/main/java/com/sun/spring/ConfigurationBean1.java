package com.sun.spring;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ConfigurationBean1 {

    private String id;
    private String name;

}
