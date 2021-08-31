package com.epam.rd.at.java_file_formats;

import com.epam.rd.at.java_file_formats.generic.BaseDataObject;
import com.epam.rd.at.java_file_formats.generic.Builder;
import com.epam.rd.at.java_file_formats.generic.BuilderFactory;
import com.epam.rd.at.java_file_formats.generic.BuilderType;
import com.epam.rd.at.java_file_formats.generic.DataGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.platform.commons.support.ReflectionSupport;
import org.junit.platform.commons.util.ClassFilter;
import org.junit.platform.commons.util.ReflectionUtils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.temporal.Temporal;
import java.util.List;
import java.util.Properties;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SuppressWarnings("unchecked")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BuilderTest {

    private BaseDataObject dataObject;
    private String packageName;

    @BeforeAll
    public void generateData() {
        try (InputStream inputStream = Files.newInputStream(Paths.get("src/test/resources/test.properties"))) {
            Properties properties = new Properties();
            properties.load(inputStream);
            packageName = properties.getProperty("domain_package");
        } catch (IOException e) {
            throw new IllegalArgumentException("Properties file is not read correctly");
        }

        DataGenerator<?> dataGenerator = getInterfaceImplementation(DataGenerator.class);
        dataObject = dataGenerator.generateData();
    }

    @ParameterizedTest
    @EnumSource(BuilderType.class)
    public void testBuilderType(BuilderType builderType) {
        BuilderFactory<BaseDataObject> builderFactory = getInterfaceImplementation(BuilderFactory.class);
        Builder<BaseDataObject> builder = builderFactory.create(builderType);
        String generatedText = builder.generate(dataObject);
        System.out.println(generatedText);
        BaseDataObject read = builder.parse(generatedText);

        // check objects equal
        deepEquals(dataObject, read, "$");
        assertEquals(dataObject, read); // native equals at last
    }

    private void deepEquals(Object object1, Object object2, String fieldName) {
        if (object1 == null || object2 == null) {
            assertNull(object1, fieldName);
            assertNull(object2, fieldName);
        } else {
            if (object1 instanceof List) {
                List<?> list1 = (List<?>) object1;
                List<?> list2 = (List<?>) object2;
                assertEquals(list1.size(), list2.size(), fieldName + ".size()");
                for (int i = 0; i < list1.size(); i++) {
                    deepEquals(list1.get(i), list2.get(i), fieldName + "[" + i + "]");
                }
            } else {
                assertEquals(object1.getClass(), object2.getClass());
                Class<?> clazz = object1.getClass();
                if (Stream.of(String.class, Number.class, Character.class, Boolean.class, Temporal.class)
                        .anyMatch(cls -> cls.isInstance(object1)) || clazz.isEnum()) {
                    assertEquals(object1, object2, fieldName);
                } else {
                    List<Field> declaredFields = ReflectionUtils.findFields(clazz, f -> true, ReflectionUtils.HierarchyTraversalMode.BOTTOM_UP);
                    for (Field field : declaredFields) {
                        Object fieldValue1 = getFieldValue(object1, field);
                        Object fieldValue2 = getFieldValue(object2, field);
                        deepEquals(fieldValue1, fieldValue2, fieldName + "." + field.getName());
                    }
                }
            }
        }
    }

    private <T> T getInterfaceImplementation(Class<T> interfaceClass) {
        List<Class<?>> allClassesInPackage = ReflectionUtils.findAllClassesInPackage(packageName,
                ClassFilter.of(cls -> interfaceClass.isAssignableFrom(cls) && !cls.isInterface()));
        if (allClassesInPackage.isEmpty()) {
            throw new RuntimeException(interfaceClass.getSimpleName() + " not found");
        }
        Class<T> aClass = (Class<T>) allClassesInPackage.get(0);
        return ReflectionSupport.newInstance(aClass);
    }

    public static Object getFieldValue(Object classInstance, Field declaredField) {
        return ReflectionUtils.tryToReadFieldValue(declaredField, classInstance)
                .toOptional().orElse(null); // nulls are possible
    }

}
