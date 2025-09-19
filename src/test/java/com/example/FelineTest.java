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

    @Spy
    @InjectMocks
    private Feline feline;


    @Test
    public void testEatMeat() throws Exception {
        List<String> result = feline.eatMeat();
        List<String> expected = List.of("Животные", "Птицы", "Рыба");
        assertEquals(expected, result);
    }

    @Test
    public void testGetFamily() {
        assertEquals("Кошачьи", feline.getFamily());
    }

    @Test
    public void testGetKittensNoArgs() {
        assertEquals(1, feline.getKittens());
    }

    @Test
    public void testGetKittensWithArgs() {
        assertEquals(5, feline.getKittens(5));
    }


}
