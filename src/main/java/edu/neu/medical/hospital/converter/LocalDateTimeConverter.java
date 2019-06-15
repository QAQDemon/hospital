package edu.neu.medical.hospital.converter;

import org.springframework.core.convert.converter.Converter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String s) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime date = null;
        try {
            date = LocalDateTime.parse(s,dateTimeFormatter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }
}
