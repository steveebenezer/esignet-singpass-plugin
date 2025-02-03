package com.educerts.esignet.config;

import com.educerts.esignet.audit.CustomAuditPlugin;
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