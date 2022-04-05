package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Race extends AbstractEntity {

    @NotNull
    @NotEmpty
    private String from;

    @NotNull
    @NotEmpty
    private String to;

    @ManyToOne
    @JoinColumn(name = "train_id")
    @Nullable
    private Train train;

    @OneToMany(mappedBy = "race")
    private List<Ticket> tickets = new LinkedList<>();


    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Nullable
    public Train getTrain() {
        return train;
    }

    public void setTrain(@Nullable Train train) {
        this.train = train;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }


}
