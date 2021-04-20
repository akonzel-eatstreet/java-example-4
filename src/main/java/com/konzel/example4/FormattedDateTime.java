package com.konzel.example4;

import lombok.Value;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Value
public class FormattedDateTime {
    public FormattedDateTime(LocalDateTime ldt) {
        date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(ldt);
        time = DateTimeFormatter.ofPattern("HH:mm:ss").format(ldt);
    }

    public String date;
    public String time;
}

