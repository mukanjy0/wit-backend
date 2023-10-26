package com.example.wit.entities.platform.utils;

import com.example.wit.entities.platform.domain.Platform;
import com.example.wit.entities.platform.dto.PlatformRequest;
import org.springframework.stereotype.Component;

@Component
public class PlatformUtils {
    public static Platform updatePlatform(Platform original, PlatformRequest toUpdate) {
        if (toUpdate.getName() != null) original.setName(toUpdate.getName());
        if (toUpdate.getUrl() != null) original.setUrl(toUpdate.getUrl());

        return original;
    }
}
