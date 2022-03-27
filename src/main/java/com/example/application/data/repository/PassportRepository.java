package com.example.application.data.repository;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PassportRepository extends JpaRepository<Passport, Long> {

    @Query("select c from Passport c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.thirdName) like lower(concat('%', :searchTerm, '%'))")
    List<Passport> search(@Param("searchTerm") String searchTerm);
}

