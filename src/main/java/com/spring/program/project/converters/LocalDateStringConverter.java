package com.spring.program.project.converters;

import com.fasterxml.jackson.databind.util.StdConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author DacaP
 * @version 1.0
 */
public class LocalDateStringConverter extends StdConverter<LocalDate, String> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    @Override
    public String convert(LocalDate date) {
        return date == null ? "" : date.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }
}
