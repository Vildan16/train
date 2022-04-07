package com.example.application.data.repository;

import com.example.application.data.entity.Kassa;
import com.example.application.data.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface KassaRepository extends JpaRepository<Kassa, UUID> {
    @Query("SELECT k FROM Kassa k WHERE k.number = :number")
    Kassa findByNumber(long number);
}
