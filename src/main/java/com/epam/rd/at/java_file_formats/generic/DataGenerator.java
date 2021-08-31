package com.epam.rd.at.java_file_formats.generic;

public interface DataGenerator<T extends BaseDataObject> {
    T generateData();
}
