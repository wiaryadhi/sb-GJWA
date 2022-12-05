package com.bcafinance.gjwaspringbootjpa.repos;

import com.bcafinance.gjwaspringbootjpa.models.DataUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DataUserRepo extends JpaRepository<DataUsers,Long> {

    List<DataUsers> findByUsernameContaining(String username);
    List<DataUsers> findUsersByTokenLike(String token);
    Optional<DataUsers> findByEmail(String email);
}