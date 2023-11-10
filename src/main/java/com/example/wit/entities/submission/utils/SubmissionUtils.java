package com.example.wit.entities.submission.utils;

import com.example.wit.entities.submission.domain.Submission;
import com.example.wit.entities.submission.dto.SubmissionRequest;
import org.springframework.stereotype.Component;

@Component
public class SubmissionUtils {
    public static Submission updateSubmission (Submission original, SubmissionRequest toUpdate) {
        String result = toUpdate.getResult();
        if (result != null) original.setResult(result);

        Boolean inPractice = toUpdate.getInPractice();
        if (inPractice != null) original.setInPractice(inPractice);

        String code = toUpdate.getCode();
        if (code != null) original.setCode(code);

        return original;
    }
}
