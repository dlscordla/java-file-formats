package com.epam.rd.at.java_file_formats.generic;

public interface Builder<T extends BaseDataObject> {

    T parse(String text);

    String generate(T dataObject);

}
