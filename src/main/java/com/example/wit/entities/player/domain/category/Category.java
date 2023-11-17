package com.example.wit.entities.player.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter @NoArgsConstructor @AllArgsConstructor
public enum Category {
    HONORED_ONE((short)0, "Honored one", '0', 70000, 100000),
    KING((short)1, "King", '0', 50000, 69999),
    MONARCH((short)2, "Monarch", '0', 25000, 49999),
    RULER((short)3, "Ruler", '0', 15000, 24999),
    LEGENDARY_GRANDMASTER((short)4, "Legendary grandmaster", '0', 10000, 14999),
    GRANDMASTER((short)5, "Grandmaster", '1', 8000, 9999),
    MASTER((short)6, "Master", '1', 6000, 7999),
    STAR((short)7, "Star", '2', 4500, 5999),
    RISING_STAR((short)8, "Rising star", '2', 3000, 4499),
    EXPERT((short)9, "Expert", '3', 2200, 2999),
    CHALLENGER((short)10, "Challenger", '3', 1500, 2199),
    PROMISE((short)11, "Promise", '3', 800, 1499),
    STARTER((short)12, "Starter", '4', 400, 799),
    NEWBIE((short)13,"Newbie", '4', 0, 399);

    private Short id;
    private String tag;
    private Character divisionId;
    private Integer lowerBound;
    private Integer upperBound;
}
