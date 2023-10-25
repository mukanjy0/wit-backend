package com.example.wit.entities.university.utils;

import com.example.wit.entities.university.domain.University;
import com.example.wit.entities.university.dto.UniversityRequest;
import org.springframework.stereotype.Component;

@Component
public class UniversityUtils {
    public static University updateUniversity(University original, UniversityRequest toUpdate) {
        if (toUpdate.getAcronym() != null) original.setAcronym(toUpdate.getAcronym());
        if (toUpdate.getFullName() != null) original.setFullName(toUpdate.getFullName());
        return original;
    }
}
