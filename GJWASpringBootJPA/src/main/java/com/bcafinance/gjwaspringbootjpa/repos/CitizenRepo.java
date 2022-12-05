package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.Citizen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepo extends JpaRepository<Citizen,Long> {
    Page<Citizen> findByFullNameIsContaining(String name, Pageable pageable);
}