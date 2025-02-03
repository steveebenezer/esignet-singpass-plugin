package com.educerts.esignet.audit;

import io.mosip.esignet.api.spi.AuditPlugin;
import io.mosip.esignet.core.dto.AuditDTO;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class CustomAuditPlugin implements AuditPlugin {

    @PostConstruct
    public void init() {
        System.out.println("CustomAuditPlugin bean initialized!");
    }

    @Override
    public void audit(String eventType, String eventDescription, String userId) {
        System.out.println("Audit: Event Type: " + eventType + ", Description: " + eventDescription + ", User: " + userId);
    }

    @Override
    public void logAudit(AuditDTO auditDTO) {
        System.out.println("Audit Log: " + auditDTO);
    }

    @Override
    public void logAudit(String action, String message, String idType, String id) {
        System.out.println("Audit Log - Action: " + action + ", Message: " + message);
    }
}