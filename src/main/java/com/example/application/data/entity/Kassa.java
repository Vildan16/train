package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Kassa extends AbstractEntity {
    @NotEmpty
    private long number = 0;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }
}
