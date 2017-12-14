package com.fsd.core.services.libraryservice.services;

import com.fsd.core.services.libraryservice.models.AuditEntity;

public interface AuditService {

    public boolean sendAuditInfo(AuditEntity auditEntity);

}
