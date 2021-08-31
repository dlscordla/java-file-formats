package com.epam.rd.at.java_file_formats.task.model;

public enum Type {
    VEGETABLE ("Vegetable"),
    MEAT ("Meat"),
    MUSHROOM ("Mushroom"),
    SAUCE ("Sauce");

    private final String typeName;

    Type(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeName='" + typeName + '\'' +
                '}';
    }
}
