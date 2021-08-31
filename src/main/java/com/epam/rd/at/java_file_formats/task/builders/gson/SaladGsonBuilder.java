package com.epam.rd.at.java_file_formats.task.builders.gson;

import com.epam.rd.at.java_file_formats.generic.Builder;
import com.epam.rd.at.java_file_formats.task.model.Salad;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;

public class SaladGsonBuilder implements Builder<Salad> {

    private Gson createGson() {
        return new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTimeSerializer())
                .setPrettyPrinting()
                .create();
    }

    @Override
    public Salad parse(String text) {
        return createGson().fromJson(text, Salad.class);
    }

    @Override
    public String generate(Salad dataObject) {
        return createGson().toJson(dataObject);
    }
}
