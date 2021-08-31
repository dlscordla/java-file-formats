package com.epam.rd.at.java_file_formats.task.model.generator;

import com.epam.rd.at.java_file_formats.generic.DataGenerator;
import com.epam.rd.at.java_file_formats.task.model.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

public class SaladGenerator implements DataGenerator<Salad> {

    @Override
    public Salad generateData() {
        return new Salad().withTitle("Warm salad")
                .withIngredients(Arrays.asList(
                        new Ingredient()
                                .withIngredientName("Onion")
                                .withIngredientType(Type.VEGETABLE.getTypeName())
                                .withNutritiveValues
                                        (Collections.singletonList(new NutritiveValues()
                                                .withKCal(41)
                                                .withProteins(1.4)
                                                .withFats(0.2)
                                                .withCarbos(8.2)))
                                .withVitamins("B6, C")
                                .withWeight(50)
                                .withCookingMethod(CookingMethods.ROAST.getCookingMethodName())
                                .withProductionDate(LocalDateTime.of(2020, 9, 1, 19, 45))
                                .withPrice(50),
                        new Ingredient()
                                .withIngredientName("Chicken")
                                .withIngredientType(Type.MEAT.getTypeName())
                                .withNutritiveValues
                                        (Collections.singletonList(new NutritiveValues()
                                                .withKCal(113)
                                                .withProteins(23.6)
                                                .withFats(1.9)
                                                .withCarbos(0.4)))
                                .withVitamins("B5, B6, PP")
                                .withWeight(200)
                                .withCookingMethod(CookingMethods.STEW.getCookingMethodName())
                                .withProductionDate(LocalDateTime.of(2021, 8, 27, 20, 38))
                                .withPrice(350)
                                .withOriginCountry("Russia"),
                        new Ingredient()
                                .withIngredientName("Oyster mushroom")
                                .withIngredientType(Type.MUSHROOM.getTypeName())
                                .withNutritiveValues
                                        (Collections.singletonList(new NutritiveValues()
                                                .withKCal(33)
                                                .withProteins(3.3)
                                                .withFats(0.4)
                                                .withCarbos(3.8)))
                                .withVitamins("B2, B5, PP")
                                .withWeight(150)
                                .withCookingMethod(CookingMethods.ROAST.getCookingMethodName())
                                .withProductionDate(LocalDateTime.of(2021, 8, 23, 7, 57))
                                .withPrice(100)
                                .withOriginCountry("Russia"),
                        new Ingredient()
                                .withIngredientName("Mayonnaise")
                                .withIngredientType(Type.SAUCE.getTypeName())
                                .withNutritiveValues
                                        (Collections.singletonList(new NutritiveValues()
                                                .withKCal(629)
                                                .withProteins(2.8)
                                                .withFats(67)
                                                .withCarbos(3.7)))
                                .withVitamins("E")
                                .withWeight(15)
                                .withCookingMethod(CookingMethods.RAW.getCookingMethodName())
                                .withProductionDate(LocalDateTime.of(2021, 6, 15, 16, 38))
                                .withPrice(380)
                                .withOriginCountry("Italy")
                ));
    }
}
