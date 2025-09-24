package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Spy;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FelineTest {

    @Mock
    private Animal animal;//Создал мок только для зависимости

    private static class TestableFeline extends Feline { // Создал тестовый класс для инъекции зависимости
        private final Animal testAnimal;

        public TestableFeline(Animal animal) {
            this.testAnimal = animal;
        }

        @Override
        public List<String> getFood(String animalKind) throws Exception {
            return testAnimal.getFood(animalKind);
        }
    }

    @Test
    public void testEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(animal.getFood("Хищник")).thenReturn(expectedFood);
        Feline feline = new TestableFeline(animal);
        List<String> result = feline.eatMeat();
        assertEquals(expectedFood, result);
    }

    @Test
    public void testGetFamily() {
        Feline feline = new Feline();
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensNoArgs() {
        Feline feline = new Feline();
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testGetKittensWithArgs() {
        Feline feline = new Feline();
        assertEquals(5, feline.getKittens(5));
    }

}
