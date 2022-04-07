package com.example.application.data.entity;

import com.example.application.data.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Ticket extends AbstractEntity {

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long number;

    @ManyToOne
    @JoinColumn(name = "race_id")
    @JsonIgnoreProperties({"races"})
    @Nullable
    private Race race;

    @ManyToOne(cascade=CascadeType.ALL)
    @Nullable
    private Passport owner;


    @Column(columnDefinition = "DATE")
    private LocalDate dateFrom;

    @Column(columnDefinition = "DATE")
    private LocalDate dateTo;

    @Column(columnDefinition = "TIME")
    private LocalTime timeFrom;

    @Column(columnDefinition = "TIME")
    private LocalTime timeTo;

    private long wagonNumber;

    private long seatNumber;

    private String class_;

    private long price;

    private long kassaNumber;

    public Ticket() {
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    @Nullable
    public Race getRace() {
        return race;
    }

    public void setRace(@Nullable Race race) {
        this.race = race;
    }


    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }

    public void setDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
    }

    public LocalTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalTime timeTo) {
        this.timeTo = timeTo;
    }

    public long getWagonNumber() {
        return wagonNumber;
    }

    public void setWagonNumber(long wagonNumber) {
        this.wagonNumber = wagonNumber;
    }

    public long getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(long seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getClass_() {
        return class_;
    }

    public void setClass_(String class_) {
        this.class_ = class_;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getKassaNumber() {
        return kassaNumber;
    }

    public void setKassaNumber(long kassaNumber) {
        this.kassaNumber = kassaNumber;
    }

    @Nullable
    public Passport getOwner() {
        return owner;
    }

    public void setOwner(@Nullable Passport owner) {
        this.owner = owner;
    }
}
