package com.example.se1620_he150191_nguyenhungthanh2;

import androidx.annotation.NonNull;

public class Bird {
    private int id;
    private String name;
    private String region;
    private String species;

    public Bird(){

    }

    public Bird(int id, String name, String region, String species) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.species = species;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @NonNull
    @Override
    public String toString() {
        return "Bird {"+
                "ID: " +id +
                "Name: " +name + '\''+
                "Region: "+region + '\''+
                "Species: "+ species + '\''+
                '}';
    }
}
