package com.konzel.example4;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Boot {
    public static void main(String[] args) {
        SpringApplication.run(Boot.class);
    }

    @GetMapping("/")
    public String hello() throws IOException {
        var engine = new PebbleEngine.Builder().build();
        var compiledTemplate = engine.getTemplate("hello.html");

        var dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var dateTimeNow = LocalDateTime.now();

        Map<String, Object> context = new HashMap<>();
        context.put("dateTime", dateTimeFormatter.format(dateTimeNow));

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        return writer.toString();
    }
}
