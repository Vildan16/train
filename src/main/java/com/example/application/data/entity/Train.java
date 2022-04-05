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
    private String trainNumber = "";

    @OneToMany(mappedBy = "train")
    private List<Crew> crewMembers = new LinkedList<>();

    @OneToMany(mappedBy = "train")
    private List<Race> races = new LinkedList<>();

    @OneToOne(orphanRemoval=true)
    @JoinColumn(name = "crew_id")
    @Nullable
    private Crew pilot;

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

    public String getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(String trainNumber) {
        this.trainNumber = trainNumber;
    }

    @Nullable
    public List<Crew> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(@Nullable List<Crew> crewMembers) {
        this.crewMembers = crewMembers;
    }

    @Nullable
    public Crew getPilot() {
        return pilot;
    }

    public void setPilot(@Nullable Crew pilot) {
        this.pilot = pilot;
    }

    public Train() {
    }

    public Train(String title, String trainNumber) {
        this.title = title;
        this.trainNumber = trainNumber;
    }

    public List<Race> getRaces() {
        return races;
    }

    public void setRaces(List<Race> races) {
        this.races = races;
    }
}
