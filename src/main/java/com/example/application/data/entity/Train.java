package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Train extends AbstractEntity {
    @NotBlank
    private String title;

    @NotNull
    private int trainNumber = 0;

    @OneToMany(mappedBy = "train")
    @Nullable
    private List<Crew> crewMembers = new LinkedList<>();

    @Override
    public String toString() {
        return "[" + trainNumber + "]" + " " + title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Nullable
    public List<Crew> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(@Nullable List<Crew> crewMembers) {
        this.crewMembers = crewMembers;
    }
}
