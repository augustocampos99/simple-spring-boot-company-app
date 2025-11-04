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
    public ResponseEntity getById(@PathVariable UUID id) {
        try {
            var result = this.companyService.getById(id);
            return ResponseEntity.ok(result);
        }
        catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@Validated @RequestBody CompanyRequestDTO request) {
        try {
            var result = this.companyService.save(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        }
        catch(BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody CompanyRequestDTO request) {
        try {
            var result = this.companyService.update(id, request);

            return ResponseEntity.ok(result);
        }
        catch(BadRequestException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable UUID id) {
        try {
            this.companyService.delete(id);
            return ResponseEntity.ok("ok");
        }
        catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
