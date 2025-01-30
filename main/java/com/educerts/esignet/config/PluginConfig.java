package com.example.esignet.config;

import com.example.esignet.audit.CustomAuditPlugin;
import io.mosip.esignet.api.spi.AuditPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PluginConfig {

    @Bean
    public AuditPlugin auditPlugin() {
        return new CustomAuditPlugin();
    }
}