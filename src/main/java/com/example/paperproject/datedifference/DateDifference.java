package com.example.paperproject.datedifference;

//import java.time.LocalDate;
import java.time.*;
import java.time.temporal.ChronoUnit;

// LocalDate format - (YYYY-MM-DD)

public class DateDifference {
    public static long getDaysBetween(LocalDate date1,LocalDate date2){
        return ChronoUnit.DAYS.between(date1,date2);
    }
}
