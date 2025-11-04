package com.example.company_app.domain.repositories;

import com.example.company_app.domain.entities.Company;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CompanyRepository extends JpaRepository<Company, UUID> {

    @Query("SELECT c FROM Company c ORDER BY c.createdAt DESC")
    public List<Company> findAllPagination(Pageable pageable);

}
