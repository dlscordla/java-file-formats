package com.epam.rd.at.java_file_formats.task.builders.json;

import com.epam.rd.at.java_file_formats.generic.Builder;
import com.epam.rd.at.java_file_formats.task.model.Ingredient;
import com.epam.rd.at.java_file_formats.task.model.NutritiveValues;
import com.epam.rd.at.java_file_formats.task.model.Salad;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SaladJsonBuilder implements Builder<Salad> {

    @Override
    public Salad parse(String text) {
        JsonElement rootElement = JsonParser.parseString(text); // чтение в дерево
        JsonObject saladElement = rootElement.getAsJsonObject();
        Salad salad = new Salad();
        salad.withTitle(saladElement.get("title").getAsString());

        List<Ingredient> ingredients = new ArrayList<>();
        salad.withIngredients(ingredients);
        JsonArray ingredientsArray = saladElement.getAsJsonArray("ingredient");

        for (JsonElement element : ingredientsArray) {
            Ingredient saladIngredient = new Ingredient();
            ingredients.add(saladIngredient);
            JsonObject ingredientObject = element.getAsJsonObject();

            List<NutritiveValues> nutritiveValuesList = new ArrayList<>();
            JsonArray nutritiveValuesArray = ingredientObject.getAsJsonArray("nutritive_values");
            for (JsonElement nutritiveElement : nutritiveValuesArray) {
                NutritiveValues nutritiveValues = new NutritiveValues();
                nutritiveValuesList.add(nutritiveValues);
                JsonObject nutritiveObject = nutritiveElement.getAsJsonObject();

                nutritiveValues.withKCal(nutritiveObject.get("kCal").getAsInt());
                nutritiveValues.withProteins(nutritiveObject.get("proteins").getAsDouble());
                nutritiveValues.withFats(nutritiveObject.get("fats").getAsDouble());
                nutritiveValues.withCarbos(nutritiveObject.get("carbohydrates").getAsDouble());
            }

            saladIngredient.withIngredientName(ingredientObject.get("name").getAsString())
                    .withIngredientType(ingredientObject.get("type").getAsString())
                    .withNutritiveValues(nutritiveValuesList)
                    .withVitamins(ingredientObject.get("vitamins").getAsString())
                    .withWeight(ingredientObject.get("weight").getAsInt())
                    .withCookingMethod(ingredientObject.get("cooking_method").getAsString())
                    .withProductionDate(LocalDateTime.parse(ingredientObject.get("created").getAsString()))
                    .withPrice(ingredientObject.get("price").getAsInt());

            JsonElement originElement = ingredientObject.get("country_of_origin");
            if (originElement != null) {
                saladIngredient.withOriginCountry(originElement.getAsString());
            }
        }
        return salad;
    }

    @Override
    public String generate(Salad salad) {
        JsonObject saladJson = new JsonObject();
        saladJson.addProperty("title", salad.getTitle());
        JsonArray ingredientsArray = new JsonArray();
        saladJson.add("ingredient", ingredientsArray);
        for (Ingredient ingredient : salad.getIngredients()) {
            JsonObject ingredientJson = new JsonObject();
            ingredientsArray.add(ingredientJson);
            ingredientJson.addProperty("name", ingredient.getIngredientName());
            ingredientJson.addProperty("type", ingredient.getIngredientType());

            JsonArray nutritiveValuesArray = new JsonArray();
            ingredientJson.add("nutritive_values", nutritiveValuesArray);
            for (NutritiveValues nutritiveValues : ingredient.getNutritiveValues()) {
                JsonObject nutritiveValuesJson = new JsonObject();
                nutritiveValuesArray.add(nutritiveValuesJson);
                nutritiveValuesJson.addProperty("kCal", nutritiveValues.getKCal());
                nutritiveValuesJson.addProperty("proteins", nutritiveValues.getProteins());
                nutritiveValuesJson.addProperty("fats", nutritiveValues.getFats());
                nutritiveValuesJson.addProperty("carbohydrates", nutritiveValues.getCarbos());
            }

            ingredientJson.addProperty("vitamins", ingredient.getVitamins());
            ingredientJson.addProperty("weight", ingredient.getWeight());
            ingredientJson.addProperty("cooking_method", ingredient.getCookingMethod());
            ingredientJson.addProperty("created", ingredient.getProductionDate().toString());
            ingredientJson.addProperty("price", ingredient.getPrice());
            if (ingredient.getOriginCountry() != null) {
                ingredientJson.addProperty("country_of_origin", ingredient.getOriginCountry());
            }
        }
        return saladJson.toString();
    }

}
