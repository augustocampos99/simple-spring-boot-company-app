package com.example.company_app.controllers;

import com.example.company_app.dtos.request.EmployeeRequestDTO;
import com.example.company_app.exceptions.BadRequestException;
import com.example.company_app.exceptions.NotFoundException;
import com.example.company_app.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getAll(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "50") int limit) {
        var result = this.employeeService.getAll(page, limit);
        return ResponseEntity.ok(result);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity getById(UUID id) throws Exception {
        var result = this.employeeService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity save(@Validated @RequestBody EmployeeRequestDTO request) throws Exception {
        var result = this.employeeService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable UUID id, @Validated @RequestBody EmployeeRequestDTO request) throws Exception {
        var result = this.employeeService.update(id, request);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        this.employeeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
