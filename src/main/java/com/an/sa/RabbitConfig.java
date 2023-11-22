package com.an.sa;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    @Bean
    public Queue vibraQueue() {
        return new Queue("video-analyze-vibra");
    }

    @Bean
    public Queue shenshiQueue() {
        return new Queue("video-analyze-shenshi");
    }
}
