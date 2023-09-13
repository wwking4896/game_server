package com.wwking.gameserver.mongodb.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.wwking.gameserver.mongodb.repository")
public class MongoPrimaryConfig {
}
