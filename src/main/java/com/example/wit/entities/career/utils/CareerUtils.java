package com.example.wit.entities.career.utils;

import com.example.wit.entities.career.domain.Career;
import com.example.wit.entities.career.dto.CareerRequest;
import org.springframework.stereotype.Component;

@Component
public class CareerUtils {
    public static Career updateCareer(Career original, CareerRequest toUpdate) {
        if (toUpdate.getName() != null) original.setName(toUpdate.getName());
        if (toUpdate.getDescription() != null) original.setDescription(toUpdate.getDescription());
        return original;
    }
}
