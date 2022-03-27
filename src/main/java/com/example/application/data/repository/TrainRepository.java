package com.example.application.data.repository;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Train;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainRepository extends JpaRepository<Train, UUID> {

    @Query("select c from Train c " +
            "where lower(c.title) like lower(concat('%', :searchTerm, '%')) " +
            "or lower(c.trainNumber) like lower(concat('%', :searchTerm, '%'))")
    List<Train> search(@Param("searchTerm") String searchTerm);
}
