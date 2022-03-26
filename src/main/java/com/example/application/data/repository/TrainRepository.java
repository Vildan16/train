package com.example.application.data.repository;

import com.example.application.data.entity.Train;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainRepository extends JpaRepository<Train, UUID> {

}
