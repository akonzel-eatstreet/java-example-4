package com.konzel.example4;

import lombok.Data;

@Data
public class FormattedName {
    public FormattedName(String[] fragments) {
        value = String.join(" ", fragments);
    }

    public String value;
}
