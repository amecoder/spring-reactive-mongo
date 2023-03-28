package com.innergm.chat.appconfig;


import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.concurrent.TimeUnit;


@EnableReactiveMongoRepositories
public class MongoConfig extends AbstractReactiveMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "chat";
    }

    @Bean
    public MongoClient mongoClient() {
        String uri = "mongodb://localhost:27017";
        final ConnectionString connectionString = new ConnectionString(uri);
        final MongoClientSettings.Builder mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
                .applyToConnectionPoolSettings(builder -> builder.applySettings(connectionPoolSettings()));
        return MongoClients.create(mongoClientSettings.build());
    }

    private ConnectionPoolSettings connectionPoolSettings() {
        return ConnectionPoolSettings.builder()
                .maxSize(50)
                .maxWaitTime(20, TimeUnit.SECONDS)
                .maxConnectionIdleTime(20, TimeUnit.SECONDS)
                .maxConnectionLifeTime(60, TimeUnit.SECONDS).build();
    }

}
