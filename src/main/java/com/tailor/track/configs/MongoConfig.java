package com.tailor.track.configs;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
public class MongoConfig {
    private final Environment env;

    @Autowired
    public MongoConfig(Environment env) {
        this.env = env;
    }

    @Bean
    public MongoClient mongoClient() {
        String uri = env.getProperty("spring.data.mongodb.uri");
        return MongoClients.create(uri);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongoClient(), env.getProperty("spring.data.mongodb.database"));
    }
}
