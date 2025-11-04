package com.example.company_app.dtos.request;

import com.example.company_app.domain.entities.Company;
import com.example.company_app.domain.enums.BaseStatusEnum;
import jakarta.validation.constraints.NotBlank;

public class CompanyRequestDTO {
    @NotBlank(message = "name is required")
    private String cnpj;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "description is required")
    private String description;

    @NotBlank(message = "status is required")
    private BaseStatusEnum status;

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BaseStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BaseStatusEnum status) {
        this.status = status;
    }

    public Company toEntity() {
        return new Company(){{
            cnpj = this.getCnpj().replace(".", "")
                                .replace("-", "")
                                .replace("/", "");
            name = this.getName();
            description = this.getDescription();
            status = this.getStatus();
        }};
    }
}
