package com.example.company_app.services;

import com.example.company_app.domain.entities.Employee;
import com.example.company_app.domain.repositories.EmployeeRepository;
import com.example.company_app.dtos.request.EmployeeRequestDTO;
import com.example.company_app.exceptions.BadRequestException;
import com.example.company_app.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll(int page, int limit) {
        var result = this.employeeRepository.findAllPagination(PageRequest.of(page, limit));
        return result;
    }

    public Employee getById(UUID id) throws NotFoundException {
        var result = this.employeeRepository.findById(id);

        if(result.isEmpty()) {
            throw new NotFoundException("Employee not found");
        }
        return result.get();
    }

    public Employee save(EmployeeRequestDTO employeeRequest) throws BadRequestException {
        var employee = employeeRequest.toEntity();
        employee.setCreatedAt(LocalDateTime.now());
        employee.setUpdatedAt(LocalDateTime.now());

        this.employeeRepository.save(employee);
        return employee;
    }

    public Employee update(UUID id, EmployeeRequestDTO employeeRequest) throws NotFoundException, BadRequestException {
        var employeeResult = this.employeeRepository.findById(id);

        if(employeeResult.isEmpty()) {
            throw new NotFoundException("Employee not found");
        }

        var employee = employeeResult.get();
        employee.setCpf(employeeRequest.getCpf());
        employee.setName(employeeRequest.getName());
        employee.setStatus(employeeRequest.getStatus());
        employee.setIdRole(employeeRequest.getIdRole());
        employee.setIdCompany(employeeRequest.getIdCompany());
        employee.setUpdatedAt(LocalDateTime.now());

        this.employeeRepository.save(employee);
        return employee;
    }

    public void delete(UUID id) throws NotFoundException {
        var employeeResult = this.employeeRepository.findById(id);

        if(employeeResult.isEmpty()) {
            throw new NotFoundException("Employee not found");
        }
        this.employeeRepository.deleteById(id);
    }
    
}
