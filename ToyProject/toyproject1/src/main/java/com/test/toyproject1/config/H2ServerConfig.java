package com.test.toyproject1.config;


import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.SQLException;
@Profile("local")
@Configuration
public class H2ServerConfig {

    @Bean
    public Server h2DatabaseServer() throws SQLException {
        return Server.createTcpServer().start();
    }
}
