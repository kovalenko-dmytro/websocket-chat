package com.dkovaleko.websocketchat.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class StringToLocalDateConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String s) {
        return LocalDateTime.parse(s);
    }
}
