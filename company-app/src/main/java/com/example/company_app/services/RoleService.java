package com.example.company_app.services;

import com.example.company_app.domain.entities.Role;
import com.example.company_app.domain.repositories.RoleRepository;
import com.example.company_app.dtos.request.RoleRequestDTO;
import com.example.company_app.exceptions.BadRequestException;
import com.example.company_app.exceptions.NotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> getAll(int page, int limit) {
        var result = this.roleRepository.findAllPagination(PageRequest.of(page, limit));
        return result;
    }

    public Role getById(UUID id) throws NotFoundException {
        var result = this.roleRepository.findById(id);

        if(result.isEmpty()) {
            throw new NotFoundException("Role not found");
        }

        return result.get();
    }

    public Role save(RoleRequestDTO roleRequest) throws BadRequestException {
        var role = new Role(roleRequest.getName(),
                LocalDateTime.now(),
                LocalDateTime.now());

        this.roleRepository.save(role);
        return role;
    }

    public Role update(UUID id, RoleRequestDTO roleRequest) throws NotFoundException, BadRequestException {
        var roleResult = this.roleRepository.findById(id);

        if(roleResult.isEmpty()) {
            throw new NotFoundException("Role not found");
        }

        var role = roleResult.get();
        role.setName(roleRequest.getName());
        role.setUpdatedAt(LocalDateTime.now());

        var result = this.roleRepository.save(role);
        return role;
    }

    public void delete(UUID id) throws NotFoundException {
        var roleResult = this.roleRepository.findById(id);

        if(roleResult.isEmpty()) {
            throw new NotFoundException("Role not found");
        }
        this.roleRepository.deleteById(id);
    }


}
