package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity
public class Kassa extends AbstractEntity {
    @NotEmpty
    private long number = 0;

    @OneToOne
    @Nullable
    private Ticket ticket;


    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Nullable
    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(@Nullable Ticket ticket) {
        this.ticket = ticket;
    }
}
