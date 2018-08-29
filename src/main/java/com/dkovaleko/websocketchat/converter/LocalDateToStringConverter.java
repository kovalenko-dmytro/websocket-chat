package com.dkovaleko.websocketchat.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class LocalDateToStringConverter implements Converter<LocalDateTime, String> {

    @Override
    public String convert(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.RFC_1123_DATE_TIME);
    }
}
