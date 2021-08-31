package com.epam.rd.at.java_file_formats.task.builders.jaxb;

import com.epam.rd.at.java_file_formats.generic.Builder;
import com.epam.rd.at.java_file_formats.task.model.Salad;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class SaladJaxbBuilder implements Builder<Salad> {

    @Override
    public Salad parse(String text) {
        try {
            JAXBContext context = JAXBContext.newInstance(Salad.class);
            Unmarshaller m = context.createUnmarshaller();
            return (Salad) m.unmarshal(new StringReader(text));
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generate(Salad salad) {
        try {
            JAXBContext context = JAXBContext.newInstance(Salad.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            StringWriter stringWriter = new StringWriter();
            m.marshal(salad, stringWriter);
            return stringWriter.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
