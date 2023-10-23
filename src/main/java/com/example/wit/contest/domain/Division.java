package com.example.wit.contest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @NoArgsConstructor @AllArgsConstructor
public enum Division {
    ZERO((short)0, 10000, 100000),
    ONE((short)1, 6000, 9999),
    TWO((short)2, 3000, 5999),
    THREE((short)3, 800, 2999),
    FOUR((short)4, 0, 799);
    private Short id;
    private Integer lowerBound;
    private Integer upperBound;
}
