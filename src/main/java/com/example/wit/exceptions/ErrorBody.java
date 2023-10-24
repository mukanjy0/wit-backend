package com.example.wit.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ErrorMessage {
    List<Error> errors;
}
