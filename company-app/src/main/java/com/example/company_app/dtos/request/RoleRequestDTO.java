package com.example.company_app.dtos.request;

import jakarta.validation.constraints.NotBlank;

public class RoleRequestDTO {
    @NotBlank(message = "name is required")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleRequestDTO(String name) {
        this.name = name;
    }
}
