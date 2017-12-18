package com.fsd.core.services.libraryservice.controllers;

import com.fsd.core.services.libraryservice.models.AuditEntity;
import com.fsd.core.services.libraryservice.repository.AuditRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/audit")
@Api(value = "book operations", description = "Operations pertaining to books management")
public class AuditController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Resource
    private AuditRepository auditRepository;

    @GetMapping
    public List<AuditEntity> getAllBooks() {
        return auditRepository.findAll();
    }
}
