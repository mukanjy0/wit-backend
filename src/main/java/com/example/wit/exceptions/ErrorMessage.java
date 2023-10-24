package com.example.wit.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorMessage {
    private String status;
    private String message;
}
