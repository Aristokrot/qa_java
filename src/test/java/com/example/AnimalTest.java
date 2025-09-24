package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AnimalTest {

    @Test
    public void testGetFamily() {
        Animal animal = new Animal();
        String expected = "Существует несколько семейств: заячьи, беличьи, мышиные, кошачьи, псовые, медвежьи, куньи";
        assertEquals(expected, animal.getFamily());
    }

    // убраны обычные тесты дублирующие параметизированный
    @RunWith(Parameterized.class)
    public static class AnimalParameterizedTest {
        private String animalKind;
        private List<String> expectedFood;
        private Class<? extends Exception> expectedException;

        public AnimalParameterizedTest(String animalKind, List<String> expectedFood, Class<? extends Exception> expectedException) {
            this.animalKind = animalKind;
            this.expectedFood = expectedFood;
            this.expectedException = expectedException;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Хищник", List.of("Животные", "Птицы", "Рыба"), null},
                    {"Травоядное", List.of("Трава", "Различные растения"), null},
                    {"Неизвестный", null, Exception.class}
            });
        }

        @Test
        public void testGetFoodParameterized() throws Exception {
            Animal animal = new Animal();

            if (expectedException != null) {
                try {
                    animal.getFood(animalKind);
                    fail("Expected exception was not thrown");
                } catch (Exception e) {
                    assertTrue("Wrong exception type", expectedException.isInstance(e));
                }
            } else {
                assertEquals(expectedFood, animal.getFood(animalKind));
            }
        }
    }
}

