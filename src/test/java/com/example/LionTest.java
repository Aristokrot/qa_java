package com.example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.Spy;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {

    @Mock
    private Feline felineMock;

    @Test
    public void testLionHasManeMale() throws Exception {
        Lion lion = new Lion("Самец", felineMock);
        assertTrue(lion.doesHaveMane());
    }

    @Test
    public void testLionHasNoManeFemale() throws Exception {
        Lion lion = new Lion("Самка", felineMock);
        assertFalse(lion.doesHaveMane());
    }

    @Test(expected = Exception.class)
    public void testLionInvalidSex() throws Exception {
        new Lion("Неизвестный", felineMock);
    }

    @Test
    public void testGetKittens() throws Exception {
        when(felineMock.getKittens()).thenReturn(3);

        Lion lion = new Lion("Самец", felineMock);
        assertEquals(3, lion.getKittens());
        verify(felineMock).getKittens();
    }

    @Test
    public void testGetFood() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(felineMock.getFood("Хищник")).thenReturn(expectedFood);

        Lion lion = new Lion("Самец", felineMock);
        assertEquals(expectedFood, lion.getFood());
        verify(felineMock).getFood("Хищник");
    }


    @RunWith(Parameterized.class)
    public static class LionParameterizedTest {
        private String sex;
        private boolean expectedHasMane;

        public LionParameterizedTest(String sex, boolean expectedHasMane) {
            this.sex = sex;
            this.expectedHasMane = expectedHasMane;
        }

        @Parameterized.Parameters
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][]{
                    {"Самец", true},
                    {"Самка", false}
            });
        }

        @Test
        public void testLionManeParameterized() throws Exception {
            Feline felineMock = mock(Feline.class);
            when(felineMock.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
            when(felineMock.getKittens()).thenReturn(1);

            Lion lion = new Lion(sex, felineMock);
            assertEquals(expectedHasMane, lion.doesHaveMane());
        }
    }
}
