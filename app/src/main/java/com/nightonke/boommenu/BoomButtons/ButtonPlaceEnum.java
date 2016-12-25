package com.nightonke.boommenu.BoomButtons;

/**
 * Created by Weiping Huang at 20:34 on 16/11/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public enum ButtonPlaceEnum {
    HAM_1 (35),
    HAM_2 (36),
    HAM_3 (37),
    HAM_4 (38),
    HAM_5 (39),
    HAM_6 (40),

    Horizontal(Integer.MAX_VALUE - 1),
    Vertical(Integer.MAX_VALUE),

    Unknown(-1);

    private final int value;

    ButtonPlaceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ButtonPlaceEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }

    /**
     * Get the number of boom-button for button-place-enum.
     * -1 for unknown, and MAX_INT for horizontal or vertical place-enum.
     *
     * @return the number of boom-button
     */
    public int buttonNumber() {
        switch (this) {
            case HAM_1:
                return 1;
            case HAM_2:
                return 2;
            case HAM_3:
                return 3;
            case HAM_4:
                return 4;
            case HAM_5:
                return 5;
            case HAM_6:
                return 6;
            case Horizontal:
            case Vertical:
                return Integer.MAX_VALUE;
            default:
                return -1;
        }
    }
}
