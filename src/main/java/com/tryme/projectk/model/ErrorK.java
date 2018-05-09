package com.tryme.projectk.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorK {
    private String code;
    private String message;

    public ErrorK(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
