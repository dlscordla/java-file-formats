package com.epam.rd.at.java_file_formats.task.model;

public enum CookingMethods {
    RAW ("raw - as it is"),
    ROAST ("roast over medium heat"),
    STEW ("stew until tender");

    private final String cookingMethodName;

    CookingMethods(String cookingMethodName) {
        this.cookingMethodName = cookingMethodName;
    }

    public String getCookingMethodName() {
        return cookingMethodName;
    }

    @Override
    public String toString() {
        return "CookingMethods{" +
                "cookingMethodName='" + cookingMethodName + '\'' +
                '}';
    }
}