package com.epam.rd.at.java_file_formats.task.model;

import com.epam.rd.at.java_file_formats.task.builders.gson.LocalDateTimeSerializer;
import com.epam.rd.at.java_file_formats.task.builders.jaxb.LocalDateTimeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Ingredient {

    @XmlAttribute (name = "name")
    private String nameOfIngredient;

    @XmlElement
    private String type;

    @XmlElement(name = "nutritive_values")
    private List<NutritiveValues> nutritiveValues;

    @XmlElement
    private String vitamins;

    @XmlElement
    private int weight;

    @XmlElement(name = "cooking_method")
    private String cookingMethod;

    @XmlElement(name = "created")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)

    @SerializedName("created")
    @JsonAdapter(LocalDateTimeSerializer.class)

    private LocalDateTime productionDate;

    @XmlElement (name = "price")
    private int priceOfIngredient;

    @XmlElement(name = "country_of_origin")
    private String originCountry;

    public String getIngredientName() {
        return nameOfIngredient;
    }

    public Ingredient withIngredientName(String name) {
        this.nameOfIngredient = name;
        return this;
    }

    public String getIngredientType() {
        return type;
    }

    public Ingredient withIngredientType(String type) {
        this.type = type;
        return this;
    }

    public List<NutritiveValues> getNutritiveValues() {
        return nutritiveValues;
    }

    public Ingredient withNutritiveValues(List<NutritiveValues> nutritiveValues) {
        this.nutritiveValues = nutritiveValues;
        return this;
    }

    public String getVitamins() {
        return vitamins;
    }

    public Ingredient withVitamins(String vitamins) {
        this.vitamins = vitamins;
        return this;
    }

    public Integer getWeight() {
        return weight;
    }

    public Ingredient withWeight(Integer weight) {
        this.weight = weight;
        return this;
    }

    public String getCookingMethod() {
        return cookingMethod;
    }

    public Ingredient withCookingMethod(String cookingMethod) {
        this.cookingMethod = cookingMethod;
        return this;
    }

    public LocalDateTime getProductionDate() {
        return productionDate;
    }

    public Ingredient withProductionDate(LocalDateTime productionDate) {
        this.productionDate = productionDate;
        return this;
    }

    public Integer getPrice() {
        return priceOfIngredient;
    }

    public Ingredient withPrice(Integer price) {
        this.priceOfIngredient = price;
        return this;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public Ingredient withOriginCountry(String originCountry) {
        this.originCountry = originCountry;
        return this;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + nameOfIngredient + '\'' +
                ", type='" + type + '\'' +
                ", nutritiveValues=" + nutritiveValues +
                ", vitamins='" + vitamins + '\'' +
                ", weight=" + weight +
                ", cookingMethod='" + cookingMethod + '\'' +
                ", productionDate=" + productionDate +
                ", price=" + priceOfIngredient +
                ", originCountry='" + originCountry + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ingredient that = (Ingredient) o;

        return Objects.equals(nameOfIngredient, that.nameOfIngredient)
                && Objects.equals(type, that.type)
                && Objects.equals(nutritiveValues, that.nutritiveValues)
                && Objects.equals(vitamins, that.vitamins)
                && Objects.equals(cookingMethod, that.cookingMethod)
                && Objects.equals(productionDate, that.productionDate)
                && Objects.equals(priceOfIngredient, that.priceOfIngredient)
                && Objects.equals(weight, that.weight)
                && Objects.equals(originCountry, that.originCountry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameOfIngredient, type, nutritiveValues, vitamins, weight,
                cookingMethod, productionDate, priceOfIngredient, originCountry);
    }
}
