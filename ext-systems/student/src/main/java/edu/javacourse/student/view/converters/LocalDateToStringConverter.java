package edu.javacourse.student.view.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateToStringConverter extends StdConverter<LocalDate, String> {

    private static final String PATTERN = "dd.MM.yyyy";

    @Override
    public String convert(LocalDate localDate) {
        return localDate != null ? localDate.format(DateTimeFormatter.ofPattern(PATTERN)) : "";
    }
}


