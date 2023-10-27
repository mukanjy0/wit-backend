package com.example.wit.entities.player.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter @NoArgsConstructor @AllArgsConstructor
public enum Category {
    HONORED_ONE((short)0, "Honored one", '0', "ffffff", 70000, 100000),
    KING((short)1, "King", '0', "000000", 50000, 69999),
    MONARCH((short)2, "Monarch", '0', "8B0000", 25000, 49999),
    RULER((short)3, "Ruler", '0', "4169E1", 15000, 24999),
    RISING_STAR((short)4, "Rising Star", '0', "FFD700", 10000, 14999),
    TRUE_TALENT((short)5, "True talent", '1', "ff0000", 8000, 9999),
    LEGENDARY_GRANDMASTER((short)6, "Legendary grandmaster", '1', "ff4500", 6000, 7999),
    GRANDMASTER((short)7, "Grandmaster", '2', "ff8c00", 4500, 5999),
    MASTER((short)8, "Master", '2', "ffa500", 3000, 4499),
    EXPERT((short)9, "Expert", '3', "b0c4de", 2200, 2999),
    PROMISE((short)10, "Promise", '3', "8a2be2", 1500, 2199),
    CHALLENGER((short)11, "Challenger", '3', "c0c0c0", 800, 1499),
    STARTER((short)12, "Starter", '4', "32cd32", 400, 799),
    NEWBIE((short)13,"Newbie", '4', "d3d3d3", 0, 399);

    private Short id;
    private String rank;
    private Character divisionId;
    private String color;
    private Integer lowerBound;
    private Integer upperBound;
}
