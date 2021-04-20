package com.konzel.example4;

import com.mitchellbosecke.pebble.PebbleEngine;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
public class Boot {
    private final ApplicationArguments arguments;

    public static void main(String[] args) {
        SpringApplication.run(Boot.class, args);
    }

    public Boot(ApplicationArguments arguments) {
        this.arguments = arguments;
    }

    @GetMapping("/")
    public String hello() throws IOException {
        val engine = new PebbleEngine.Builder().build();
        val compiledTemplate = engine.getTemplate("hello.html");

        val formattedDateTime = new FormattedDateTime(LocalDateTime.now());
        val formattedName = new FormattedName(arguments.getSourceArgs());

        Map<String, Object> context = new HashMap<>();
        context.put("now", formattedDateTime);
        context.put("name", formattedName);

        Writer writer = new StringWriter();
        compiledTemplate.evaluate(writer, context);

        return writer.toString();
    }
}
