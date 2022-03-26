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
    private String title = "";

    @Nullable
    public List<Crew> getCrews() {
        return crews;
    }

    @NotNull
    private int passage = 0;

    @OneToMany(mappedBy = "train")
    @Nullable
    private List<Crew> crews = new LinkedList<>();

    @Override
    public String toString() {
        return "[" + passage + "]" + " " + title;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPassage() {
        return passage;
    }

    public void setPassage(int passage) {
        this.passage = passage;
    }

    public void setCrews(@Nullable List<Crew> crews) {
        this.crews = crews;
    }
}
