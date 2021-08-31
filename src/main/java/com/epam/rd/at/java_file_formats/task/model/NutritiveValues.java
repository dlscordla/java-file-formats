package com.epam.rd.at.java_file_formats.task.model;

import javax.xml.bind.annotation.XmlElement;
import java.util.Objects;

public class NutritiveValues {

    @XmlElement(name = "kCal")
    private int kCal;

    @XmlElement(name = "proteins")
    private double proteins;

    @XmlElement(name = "fats")
    private double fats;

    @XmlElement (name = "carbohydrates")
    private double carbohydrates;

    public Integer getKCal() {
        return kCal;
    }

    public NutritiveValues withKCal(Integer kCal) {
        this.kCal = kCal;
        return this;
    }

    public Double getProteins() {
        return proteins;
    }

    public NutritiveValues withProteins(double proteins) {
        this.proteins = proteins;
        return this;
    }

    public Double getFats() {
        return fats;
    }

    public NutritiveValues withFats(double fats) {
        this.fats = fats;
        return this;
    }

    public Double getCarbos() {
        return carbohydrates;
    }

    public NutritiveValues withCarbos(double carbohydrates) {
        this.carbohydrates = carbohydrates;
        return this;
    }

    @Override
    public String toString() {
        return "NutritiveValues{" +
                "kCal=" + kCal +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NutritiveValues that = (NutritiveValues) o;
        return Objects.equals(kCal, that.kCal)
                && Objects.equals(proteins, that.proteins)
                && Objects.equals(fats, that.fats)
                && Objects.equals(carbohydrates, that.carbohydrates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kCal, proteins, fats, carbohydrates);
    }
}
