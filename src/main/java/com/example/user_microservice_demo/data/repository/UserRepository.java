package com.example.user_microservice_demo.data.repository;

import com.example.user_microservice_demo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = """
            SELECT u FROM User u WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :nameTerm, '%'))
            """)
    List<User> findAllByName(String nameTerm);

    @Query(value = """
            SELECT u FROM User u
            JOIN Country c ON c.id = u.country.id
            where c.name like lower(CONCAT('%', :nameTerm, '%'))
            OR c.code like lower(CONCAT('%', :nameTerm, '%'))
            """)
    List<User> findAllByCountryTerm(String term);
}
