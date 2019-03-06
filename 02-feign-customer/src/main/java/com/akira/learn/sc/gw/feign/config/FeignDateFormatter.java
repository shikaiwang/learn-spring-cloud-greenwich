package com.akira.learn.sc.gw.feign.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Component
public class FeignDateFormatter implements Formatter<Date> {
    @Value("${spring.jackson.date-format}")
    private String format;
    private final ThreadLocal<DateFormat> dateFormatThreadLocal = new ThreadLocal<DateFormat>() {
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat(format);
        }
    };


    @Override
    public Date parse(String s, Locale locale) throws ParseException {
        return dateFormatThreadLocal.get().parse(s);
    }

    @Override
    public String print(Date date, Locale locale) {
        return dateFormatThreadLocal.get().format(date);
    }
}
