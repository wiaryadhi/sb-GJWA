package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.Azim;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AzimRepo extends JpaRepository<Azim,Long> {

    Optional<Azim> findByEmail(String email);
}
