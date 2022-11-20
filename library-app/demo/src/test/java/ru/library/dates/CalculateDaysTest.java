package ru.library.dates;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculateDaysTest {

    @Test
    void calcDays() throws ParseException {
        CalculateDays firstCouple = new CalculateDays("2022-11-20", "2022-11-20");
        assertEquals(0, firstCouple.calcDays());

        CalculateDays secondCouple = new CalculateDays("2022-11-20", "2022-11-30");
        assertEquals(10, secondCouple.calcDays());

        CalculateDays thirdCouple = new CalculateDays("2022-10-20", "2022-11-20");
        assertEquals(31, thirdCouple.calcDays());

        CalculateDays fourthCouple = new CalculateDays("2021-10-20", "2022-10-20");
        assertEquals(365, fourthCouple.calcDays());

        CalculateDays fifthCouple = new CalculateDays("2022-01-20", "2022-10-20");
        assertEquals(273, fifthCouple.calcDays());
    }
}