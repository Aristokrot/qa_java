package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.List;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CatTest {

    @Mock
    private Feline felineMock;

    @Test
    public void testGetSound() {
        Cat cat = new Cat(felineMock);
        String expectedSound = "Мяу";
        assertEquals(expectedSound, cat.getSound());
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(felineMock.eatMeat()).thenReturn(expectedFood);

        Cat cat = new Cat(felineMock);
        assertEquals(expectedFood, cat.getFood());
        verify(felineMock).eatMeat();
    }

    @Test(expected = Exception.class)
    public void testGetFoodException() throws Exception {
        when(felineMock.eatMeat()).thenThrow(new Exception("Ошибка"));

        Cat cat = new Cat(felineMock);
        cat.getFood();
    }
}
