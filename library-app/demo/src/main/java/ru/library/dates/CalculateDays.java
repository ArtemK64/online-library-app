package ru.library.dates;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Data
public class CalculateDays {
    private String firstDate;
    private String secondDate;

    public CalculateDays(String firstDate, String secondDate) {
        this.firstDate = firstDate;
        this.secondDate = secondDate;
    }

    public long calcDays() throws ParseException {
        SimpleDateFormat dateTimeFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String hadTakenDate = firstDate;
        String hadReturnedDate = secondDate;

        Date firstDate = dateTimeFormatter.parse(hadTakenDate);
        Date secondDate = dateTimeFormatter.parse(hadReturnedDate);

        return TimeUnit.DAYS.convert(secondDate.getTime() - firstDate.getTime(), TimeUnit.MILLISECONDS);
    }
}