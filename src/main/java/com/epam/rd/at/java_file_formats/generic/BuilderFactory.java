package com.epam.rd.at.java_file_formats.generic;

public interface BuilderFactory<T extends BaseDataObject> {
    Builder<T> create(BuilderType builderType);
}
