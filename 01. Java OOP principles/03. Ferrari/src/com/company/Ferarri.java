package com.company;

public class Ferarri implements FerarriFunc {

    private String breaks;
    private String gasPedal;

    @Override
    public String getBreaks() {
        return breaks;
    }

    @Override
    public String getGasPedal() {
        return gasPedal;
    }

    public Ferarri() {
        this.breaks = "Brakes!";
        this.gasPedal = "Zadu6avam sA!";
    }
}
