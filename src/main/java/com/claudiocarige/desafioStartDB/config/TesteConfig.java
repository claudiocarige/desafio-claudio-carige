package com.claudiocarige.desafioStartDB.config;

import com.claudiocarige.desafioStartDB.services.impl.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public void startDB() {
        dbService.startDB();
    }

}
