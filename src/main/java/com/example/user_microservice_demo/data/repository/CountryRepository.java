package com.example.user_microservice_demo.data.repository;


import com.example.user_microservice_demo.data.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c FROM Country c WHERE c.code = :code")
    Optional<Country> findByCode(String code);
}
