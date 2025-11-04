package com.example.company_app.services;

import com.example.company_app.domain.entities.Company;
import com.example.company_app.domain.repositories.CompanyRepository;
import com.example.company_app.dtos.request.CompanyRequestDTO;
import com.example.company_app.exceptions.BadRequestException;
import com.example.company_app.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyService {
    
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAll(int page, int limit) {
        var result = this.companyRepository.findAllPagination(PageRequest.of(page, limit));
        return result;
    }

    public Company getById(UUID id) throws NotFoundException {
        var result = this.companyRepository.findById(id);

        if(result.isEmpty()) {
            throw new NotFoundException("Company not found");
        }

        return result.get();
    }

    public Company save(CompanyRequestDTO companyRequest) throws BadRequestException {
        var company = companyRequest.toEntity();
        company.setCreatedAt(LocalDateTime.now());
        company.setUpdatedAt(LocalDateTime.now());

        this.companyRepository.save(company);
        return company;
    }

    public Company update(UUID id, CompanyRequestDTO companyRequest) throws NotFoundException, BadRequestException {
        var companyResult = this.companyRepository.findById(id);

        if(companyResult.isEmpty()) {
            throw new NotFoundException("Company not found");
        }

        var company = companyResult.get();
        company.setName(companyRequest.getName());
        company.setDescription(companyRequest.getDescription());
        company.setStatus(companyRequest.getStatus());
        company.setUpdatedAt(LocalDateTime.now());

        this.companyRepository.save(company);
        return company;
    }

    public void delete(UUID id) throws NotFoundException {
        var companyResult = this.companyRepository.findById(id);

        if(companyResult.isEmpty()) {
            throw new NotFoundException("Company not found");
        }
        this.companyRepository.deleteById(id);
    }
}
