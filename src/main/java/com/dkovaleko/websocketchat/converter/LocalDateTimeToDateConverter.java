package com.dkovaleko.websocketchat.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class LocalDateTimeToDateConverter implements Converter<LocalDateTime, Date> {

    @Override
    public Date convert(LocalDateTime localDateTime) {
        return Date.valueOf(String.valueOf(localDateTime));
    }
}
