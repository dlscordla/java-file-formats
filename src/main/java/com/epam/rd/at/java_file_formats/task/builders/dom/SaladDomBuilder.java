package com.epam.rd.at.java_file_formats.task.builders.dom;

import com.epam.rd.at.java_file_formats.generic.Builder;
import com.epam.rd.at.java_file_formats.task.model.Ingredient;
import com.epam.rd.at.java_file_formats.task.model.NutritiveValues;
import com.epam.rd.at.java_file_formats.task.model.Salad;
import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Objects;

public class SaladDomBuilder implements Builder<Salad> {
    private void addSubElement(Document document, Node targetNode, String newElementName, String value) {
        Element element = document.createElement(newElementName);
        element.setTextContent(value);
        targetNode.appendChild(element);
    }

    @Override
    public Salad parse(String text) {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(text)));

            Element saladElement = document.getDocumentElement();
            Salad salad = new Salad();
            salad.withTitle(getElementContent(saladElement, "title"));

            ArrayList<Ingredient> ingredients = new ArrayList<>();
            salad.withIngredients(ingredients);
            NodeList ingredientNodes = saladElement.getElementsByTagName("ingredient");

            for (int i = 0; i < ingredientNodes.getLength(); i++) {
                Element ingredientElement = (Element) ingredientNodes.item(i);
                Ingredient saladIngredient = new Ingredient();
                ingredients.add(saladIngredient);

                saladIngredient
                        .withIngredientName(ingredientElement.getAttribute("name"));
                saladIngredient
                        .withIngredientType(getElementContent(ingredientElement, "type"));

                ArrayList<NutritiveValues> nutritiveValues = new ArrayList<>();
                saladIngredient.withNutritiveValues(nutritiveValues);
                NodeList nutritiveValuesNodes = ingredientElement.getElementsByTagName("nutritive_values");

                for (int j = 0; j < nutritiveValuesNodes.getLength(); j++) {
                    Element nutritiveValuesElement = (Element) nutritiveValuesNodes.item(j);
                    NutritiveValues nutritiveValuesOfIngredient = new NutritiveValues();
                    nutritiveValues.add(nutritiveValuesOfIngredient);

                    nutritiveValuesOfIngredient
                            .withKCal(Integer.parseInt(Objects
                                    .requireNonNull(getElementContent(nutritiveValuesElement, "kCal"))));
                    nutritiveValuesOfIngredient
                            .withProteins(Double.parseDouble(Objects
                                    .requireNonNull(getElementContent(nutritiveValuesElement, "proteins"))));
                    nutritiveValuesOfIngredient
                            .withFats(Double.parseDouble(Objects
                                    .requireNonNull(getElementContent(nutritiveValuesElement, "fats"))));
                    nutritiveValuesOfIngredient
                            .withCarbos(Double.parseDouble(Objects
                                    .requireNonNull(getElementContent(nutritiveValuesElement, "carbohydrates"))));
                }

                saladIngredient
                        .withVitamins(getElementContent(ingredientElement, "vitamins"));
                saladIngredient
                        .withWeight(Integer.parseInt(Objects
                                .requireNonNull(getElementContent(ingredientElement, "weight"))));
                saladIngredient
                        .withCookingMethod(getElementContent(ingredientElement, "cooking_method"));
                saladIngredient
                        .withProductionDate(LocalDateTime.parse(Objects
                                .requireNonNull(getElementContent(ingredientElement, "created"))));
                saladIngredient
                        .withPrice(Integer.parseInt(Objects
                                .requireNonNull(getElementContent(ingredientElement, "price"))));
                saladIngredient
                        .withOriginCountry(getElementContent(ingredientElement, "country_of_origin"));
            }
            return salad;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getElementContent(Element element, String name) {
        Node item = element.getElementsByTagName(name).item(0);
        Element singleElement = (Element) item;
        return singleElement != null ? singleElement.getTextContent() : null;
    }

    @Override
    public String generate(Salad salad) {
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element saladElement = document.createElement("salad");
            document.appendChild(saladElement);
            addSubElement(document, saladElement, "title", salad.getTitle());
            for (Ingredient ingredient : salad.getIngredients()) {
                Element ingredientElement = document.createElement("ingredient");
                saladElement.appendChild(ingredientElement);
                ingredientElement.setAttribute("name", ingredient.getIngredientName());
                addSubElement(document, ingredientElement, "type", ingredient.getIngredientType());

                Element nutritiveValueElement = document.createElement("nutritive_values");
                for (NutritiveValues nutritiveValues : ingredient.getNutritiveValues()) {
                    addSubElement(document, nutritiveValueElement, "kCal", nutritiveValues.getKCal().toString());
                    addSubElement(document, nutritiveValueElement, "proteins", nutritiveValues.getProteins().toString());
                    addSubElement(document, nutritiveValueElement, "fats", nutritiveValues.getFats().toString());
                    addSubElement(document, nutritiveValueElement, "carbohydrates", nutritiveValues.getCarbos().toString());
                }
                ingredientElement.appendChild(nutritiveValueElement);

                addSubElement(document, ingredientElement, "vitamins", ingredient.getVitamins());
                addSubElement(document, ingredientElement, "weight", ingredient.getWeight().toString());
                addSubElement(document, ingredientElement, "cooking_method", ingredient.getCookingMethod());
                addSubElement(document, ingredientElement, "created", ingredient.getProductionDate().toString());
                addSubElement(document, ingredientElement, "price", ingredient.getPrice().toString());
                if (ingredient.getOriginCountry() != null) {
                    addSubElement(document, ingredientElement, "country_of_origin", ingredient.getOriginCountry());
                }
            }
            return transformDocument(document);
        } catch (IOException | ParserConfigurationException | TransformerException | DOMException e) {
            throw new RuntimeException(e);
        }
    }

    private String transformDocument(Document document) throws TransformerException, IOException {

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes"); // отступы

        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
        Source source = new DOMSource(document); //

        StringWriter stringWriter = new StringWriter();
        transformer.transform(source, new StreamResult(stringWriter));
        return stringWriter.getBuffer().toString();
    }
}