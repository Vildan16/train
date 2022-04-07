package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
public class Kassa extends AbstractEntity {
    @NotEmpty
    private long number = 0;

    @OneToMany
    @Nullable
    private List<Ticket> tickets;


    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Nullable
    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(@Nullable List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
