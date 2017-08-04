package com.spaneos.jerseysample.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories("com.spaneos.jerseysample.repository")
public class JerseySampleConfig {

}
