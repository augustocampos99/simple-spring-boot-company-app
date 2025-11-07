package com.example.company_app.dtos.request;

import com.example.company_app.domain.entities.Employee;
import com.example.company_app.domain.enums.BaseStatusEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class EmployeeRequestDTO {
    @NotBlank(message = "cpf is required")
    private String cpf;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "status is required")
    private BaseStatusEnum status;

    @NotNull(message = "idRole is required")
    private UUID idRole;

    @NotNull(message = "idCompany is required")
    private UUID idCompany;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BaseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BaseStatusEnum status) {
        this.status = status;
    }

    public UUID getIdRole() {
        return idRole;
    }

    public void setIdRole(UUID idRole) {
        this.idRole = idRole;
    }

    public UUID getIdCompany() {
        return idCompany;
    }

    public void setIdCompany(UUID idCompany) {
        this.idCompany = idCompany;
    }

    public Employee toEntity() {
        Employee employee = new Employee();
        employee.setCpf(this.getCpf().replace(".", "").replace("-", ""));
        employee.setName(this.getName());
        employee.setStatus(this.getStatus());
        employee.setIdRole(this.getIdRole());
        employee.setIdCompany(this.getIdCompany());
        return employee;
    }

}
