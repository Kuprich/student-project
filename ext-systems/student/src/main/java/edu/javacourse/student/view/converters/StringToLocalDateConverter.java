package edu.javacourse.student.view.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateConverter extends StdConverter<String, LocalDate> {

    private static final String PATTERN = "dd.MM.yyyy";

    @Override
    public LocalDate convert(String s) {
        try {
            return LocalDate.parse(s, DateTimeFormatter.ofPattern(PATTERN));
        } catch (Exception ex){
            return null;
        }
    }
}
