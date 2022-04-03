package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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
    @JsonIgnoreProperties({"races"})
    @Nullable
    private Train train;


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
}
