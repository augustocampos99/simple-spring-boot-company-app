package com.example.company_app.services;

import com.example.company_app.domain.entities.Company;
import com.example.company_app.domain.enums.BaseStatusEnum;
import com.example.company_app.domain.repositories.CompanyRepository;
import com.example.company_app.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @BeforeAll
    public static void beforeAll() {
        MockitoAnnotations.openMocks(CompanyServiceTest.class);
    }

    @Test
    public void ShouldReturnListOfCompany() {
        var company = this.createCompany();
        List<Company> expected = List.of(company, company, company);

        Mockito.when(this.companyRepository.findAllPagination(Mockito.any())).thenReturn(expected);

        var result = this.companyService.getAll(1, 10);

        Assertions.assertEquals(expected, result);
    }

    @Test
    public void ShouldReturnOneCompany() throws NotFoundException {
        Optional<Company> expected = Optional.of(this.createCompany());

        Mockito.when(this.companyRepository.findById(Mockito.any())).thenReturn(expected);

        var result = this.companyService.getById(UUID.randomUUID());

        Assertions.assertEquals(expected.get(), result);
    }

    @Test
    public void ShouldReturnOneCompanyException() throws NotFoundException {
        Optional<Company> expected = Optional.empty();

        Mockito.when(this.companyRepository.findById(Mockito.any())).thenReturn(expected);

        Assertions.assertThrows(NotFoundException.class, () -> this.companyService.getById(UUID.randomUUID()));
    }

    private Company createCompany() {
        return new Company(
                "12345678000100",
                "Company Name",
                "Description Company",
                BaseStatusEnum.ACTIVE,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

}
