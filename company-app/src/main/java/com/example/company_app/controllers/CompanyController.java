package com.example.company_app.controllers;

import com.example.company_app.dtos.request.CompanyRequestDTO;
import com.example.company_app.exceptions.BadRequestException;
import com.example.company_app.exceptions.NotFoundException;
import com.example.company_app.services.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "50") int limit) {
        var result = this.companyService.getAll(page, limit);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(@PathVariable UUID id) throws Exception {
        var result = this.companyService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@Validated @RequestBody CompanyRequestDTO request) throws Exception {
        var result = this.companyService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody CompanyRequestDTO request) throws Exception {
        var result = this.companyService.update(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        this.companyService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
