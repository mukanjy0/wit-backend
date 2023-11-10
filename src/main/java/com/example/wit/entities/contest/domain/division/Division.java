package com.example.wit.entities.contest.domain.division;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter @NoArgsConstructor @AllArgsConstructor
public enum Division {
    ZERO('0', 10000, 100000),
    ONE('1', 6000, 9999),
    TWO('2', 3000, 5999),
    THREE('3', 800, 2999),
    FOUR('4', 0, 799);
    private Character id;
    private Integer lowerBound;
    private Integer upperBound;
}
