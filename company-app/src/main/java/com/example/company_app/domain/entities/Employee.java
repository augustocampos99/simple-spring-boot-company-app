package com.example.company_app.domain.entities;

import com.example.company_app.domain.enums.BaseStatusEnum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "cpf")
    private String CPF;

    private String name;

    @Enumerated(EnumType.STRING)
    private BaseStatusEnum status;

    @Column(name = "id_role")
    private UUID idRole;

    @Column(name = "id_company")
    private UUID idCompany;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return CPF;
    }

    public void setCpf(String cpf) {
        this.CPF = cpf;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Employee(){}

    public Employee(String name, String cpf, BaseStatusEnum status, UUID idRole, UUID idCompany, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.CPF = cpf;
        this.status = status;
        this.idRole = idRole;
        this.idCompany = idCompany;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
