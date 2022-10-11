package com.practice.springsecondphrasepractice.Annotation.DateTime;

import com.practice.springsecondphrasepractice.Annotation.DateTime.DateTimePattern;
import com.practice.springsecondphrasepractice.exception.DataNotFoundException;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Objects;


public class DateTimeValidator implements ConstraintValidator<DateTimePattern, String> {

    private DateTimeFormatter formatter;
    private String message;

    @Override
    public void initialize(DateTimePattern pattern) {
        formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern(pattern.pattern().replaceAll("[yY]", "u")))
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .toFormatter(Locale.TAIWAN)
                .withZone(ZoneId.systemDefault())
                .withResolverStyle(ResolverStyle.STRICT);
        message = pattern.message();
    }

    @Override
    @SneakyThrows
    public boolean isValid(String dateTime, ConstraintValidatorContext context) {
        if (Objects.isNull(dateTime) || dateTime.isEmpty()) {
            return true;
        }
        try {
            LocalDateTime.parse(dateTime, formatter);
            return true;
        } catch (DateTimeParseException e) {
            throw new DataNotFoundException(message);
        }
    }
}