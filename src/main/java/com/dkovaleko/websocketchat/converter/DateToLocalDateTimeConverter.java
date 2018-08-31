package com.dkovaleko.websocketchat.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class DateToLocalDateTimeConverter implements Converter<Date, LocalDateTime> {

    @Override
    public LocalDateTime convert(Date date) {
        return LocalDateTime.from(date.toLocalDate());
    }
}
