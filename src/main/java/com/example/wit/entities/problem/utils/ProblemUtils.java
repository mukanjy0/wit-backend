package com.example.wit.entities.problem.utils;

import com.example.wit.entities.problem.domain.Problem;
import com.example.wit.entities.problem.dto.ProblemRequest;
import org.springframework.stereotype.Component;

@Component
public class ProblemUtils {
    public static Problem updateProblem (Problem original, ProblemRequest toUpdate) {
        String statement = toUpdate.getStatement();
        if (statement != null) original.setStatement(statement);

        Integer memoryLimit = toUpdate.getMemoryLimit();
        if (memoryLimit != null) original.setMemoryLimit(memoryLimit);

        Short timeLimit = toUpdate.getTimeLimit();
        if (timeLimit !=  null) original.setTimeLimit(timeLimit);

        String sourceUrl = toUpdate.getSourceUrl();
        if (sourceUrl != null) original.setSourceUrl(sourceUrl);

        return original;
    }
}
