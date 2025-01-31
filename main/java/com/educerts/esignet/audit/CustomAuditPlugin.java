package com.educerts.esignet.audit;

import io.mosip.esignet.api.spi.AuditPlugin;
import io.mosip.esignet.core.dto.AuditDTO;

@Component
public class CustomAuditPlugin implements AuditPlugin {
    @Override
    public void audit(String eventType, String eventDescription, String userId) {
        // Example using slf4j:
        // log.info("Audit: Event Type: {}, Description: {}, User: {}", eventType, eventDescription, userId);
        System.out.println("Audit: Event Type: " + eventType + ", Description: " + eventDescription + ", User: " + userId);
    }

    @Override
    public void logAudit(AuditDTO auditDTO) {
        // Log audit data to console (replace with actual logging logic)
        System.out.println("Audit Log: " + auditDTO);
    }

    @Override
    public void logAudit(String action, String message, String idType, String id) {
        // Log audit data to console (replace with actual logging logic)
        System.out.println("Audit Log - Action: " + action + ", Message: " + message);
    }
}