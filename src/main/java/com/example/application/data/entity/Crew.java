package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;


@Entity
public class Crew extends AbstractEntity {

    @NotEmpty
    private String firstName = "";

    @NotEmpty
    private String lastName = "";

    @NotEmpty
    private String thirdName = "";

    private Long amount = 0L;


    @ManyToOne
    @JoinColumn(name = "train_id")
    @JsonIgnoreProperties({"crews"})
    @Nullable
    private Train train;

    @OneToOne
    @JoinColumn(name = "pasport_id")
    private Passport passport;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + thirdName;
    }


}

