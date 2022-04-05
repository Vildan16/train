package com.example.application.data.repository;

import com.example.application.data.entity.Race;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RaceRepository extends JpaRepository<Race, Long> {

    @Query("select c from Race c " +
            "where lower(c.placeFrom) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.placeTo) like lower(concat('%', :searchTerm, '%'))")
    List<Race> search(@Param("searchTerm") String searchTerm);
}
