package com.DDIS.personalCategory.Command.domain.aggregate;

import lombok.Getter;

@Getter
public enum PersonalCategoryColor {

    // figma 색
    RED("rgba(255, 140, 154, 1)"),
    ORANGE("rgba(255, 170, 140, 1)"),
    LIGHT_GREEN("rgba(197, 230, 149, 1)"),
    TURQUOISE("rgba(80, 212, 198, 1)"),
    SKY_BLUE("rgba(140, 194, 255, 1)"),
    PURPLE("rgba(174, 140, 255, 1)"),
    VIOLET("rgba(193, 132, 202, 1)");

    private final String rgba;

    PersonalCategoryColor(String rgba) {
        this.rgba = rgba;
    }

    public static boolean isValid(String rgba) {
        for (PersonalCategoryColor color : values()) {
            if (color.getRgba().equals(rgba)) {
                return true;
            }
        }
        return false;
    }
}
