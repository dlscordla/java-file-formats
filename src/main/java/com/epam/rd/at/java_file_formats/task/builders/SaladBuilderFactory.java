package com.epam.rd.at.java_file_formats.task.builders;

import com.epam.rd.at.java_file_formats.generic.Builder;
import com.epam.rd.at.java_file_formats.generic.BuilderFactory;
import com.epam.rd.at.java_file_formats.generic.BuilderType;
import com.epam.rd.at.java_file_formats.task.builders.dom.SaladDomBuilder;
import com.epam.rd.at.java_file_formats.task.builders.gson.SaladGsonBuilder;
import com.epam.rd.at.java_file_formats.task.builders.jaxb.SaladJaxbBuilder;
import com.epam.rd.at.java_file_formats.task.builders.json.SaladJsonBuilder;
import com.epam.rd.at.java_file_formats.task.model.Salad;

public class SaladBuilderFactory implements BuilderFactory<Salad> {
    public Builder<Salad> create(BuilderType builderType) {
        switch (builderType) {
            case XML:
                return new SaladDomBuilder();
            case JSON_TREE:
                return new SaladJsonBuilder();
            case JAXB:
                return new SaladJaxbBuilder();
            case JSON_BIND:
               return new SaladGsonBuilder();
            default:
                throw new IllegalArgumentException();
        }
    }
}
