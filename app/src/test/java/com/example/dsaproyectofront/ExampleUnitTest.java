package com.example.dsaproyectofront;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {






    @Before
    public void setUp() throws Exception {



    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void dameMapa() {

        String mapa = Juego.dameMapa("1");
        assertEquals(null,mapa);

    }
}