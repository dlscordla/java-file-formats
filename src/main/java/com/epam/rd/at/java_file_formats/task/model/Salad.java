package com.epam.rd.at.java_file_formats.task.model;

import com.epam.rd.at.java_file_formats.generic.BaseDataObject;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

@XmlRootElement
public class Salad implements BaseDataObject {

    @XmlElement
    private String title;

    @XmlElement (name = "ingredient")
    private List<Ingredient> ingredients;

    public String getTitle() {
        return title;
    }

    public Salad withTitle(String title) {
        this.title = title;
        return this;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public Salad withIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    @Override
    public String toString() {
        return "Salad{" +
                "title='" + title + '\'' +
                ", ingredients=" + ingredients +
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
        Salad salad = (Salad) o;
        return Objects.equals(title, salad.title) && Objects.equals(ingredients, salad.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, ingredients);
    }
}
