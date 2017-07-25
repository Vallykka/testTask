package com.cft.testTask.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author vallykka
 */
@SpringBootConfiguration
@PropertySource(value = "classpath:/config.properties")
public class AppConfig {
}
