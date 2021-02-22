package com.canghai.blog.common.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:blog.properties"})
@ConfigurationProperties(prefix = "blog")
public class BlogProperties {
    private ShiroProperties shiro = new ShiroProperties();
}
