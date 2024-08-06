package org.example.aspect;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({LoggingProperties.class})
public class LoggingAutoConfiguration {

    @Bean
    public LoggingAspectWithParam loggingAspectWithParam(LoggingProperties properties) {
        return new LoggingAspectWithParam(properties);
    }

    @Bean
    public  LoggingAspectNoParam loggingAspectNoParam(LoggingProperties properties) {
        return new LoggingAspectNoParam(properties);
    }
}
