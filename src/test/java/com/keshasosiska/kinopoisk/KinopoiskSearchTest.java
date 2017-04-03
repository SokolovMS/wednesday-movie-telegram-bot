package com.keshasosiska.kinopoisk;

import static com.keshasosiska.kinopoisk.KinopoiskSearch.getMovieUrl;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class KinopoiskSearchTest {
    @Test
    public void canGetCorrectUrlForMovie1() {
        String movieName = "Лекарство от здоровья";
        String expected = "%CB%E5%EA%E0%F0%F1%F2%E2%EE+%EE%F2+%E7%E4%EE%F0%EE%E2%FC%FF";

        String actual = getMovieUrl(movieName);

        assertEquals(expected, actual);
    }
}
