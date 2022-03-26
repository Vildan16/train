package com.example.application.data.repository;

import com.example.application.data.entity.Crew;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CrewRepository extends JpaRepository<Crew, Long> {

    @Query("select c from Crew c " +
            "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))" +
            "or lower(c.thirdName) like lower(concat('%', :searchTerm, '%'))")
    List<Crew> search(@Param("searchTerm") String searchTerm);
}

