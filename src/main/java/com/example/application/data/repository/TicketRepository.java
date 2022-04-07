package com.example.application.data.repository;

import com.example.application.data.entity.Crew;
import com.example.application.data.entity.Ticket;
import com.example.application.data.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    public List<Ticket> findAllByOwnerIsNull();
    public List<Ticket> findAllByOwnerIsNotNull();

}

