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
    private String placeFrom;

    @NotNull
    private String placeTo;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;


    public String getPlaceFrom() {
        return placeFrom;
    }

    public void setPlaceFrom(String placeFrom) {
        this.placeFrom = placeFrom;
    }

    public String getPlaceTo() {
        return placeTo;
    }

    public void setPlaceTo(String placeTo) {
        this.placeTo = placeTo;
    }

    @Nullable
    public Train getTrain() {
        return train;
    }

    public void setTrain(@Nullable Train train) {
        this.train = train;
    }

}
