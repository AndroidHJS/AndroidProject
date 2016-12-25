package com.nightonke.boommenu.Piece;

/**
 * Created by Weiping Huang at 23:48 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */
public enum PiecePlaceEnum {
    HAM_1  (35),
    HAM_2  (36),
    HAM_3  (37),
    HAM_4  (38),
    HAM_5  (39),
    HAM_6  (40),
    Share  (99999),
    Unknown(-1);

    private final int value;

    PiecePlaceEnum(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static PiecePlaceEnum getEnum(int value) {
        if (value < 0 || value >= values().length) return Unknown;
        return values()[value];
    }

    /**
     * Get number of pieces from a piece-place-enum.
     *
     * @return the number of pieces
     */
    public int pieceNumber() {
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
            default:
                return -1;
        }
    }

    /**
     * Get the minimum piece number for this piecePlaceEnum
     *
     * @return minimum piece number
     */
    public int minPieceNumber() {
        switch (this) {
            case Share:
                return 3;
            default:
                return -1;
        }
    }

    /**
     * Get the maximum piece number for this piecePlaceEnum
     *
     * @return maximum piece number
     */
    public int maxPieceNumber() {
        switch (this) {
            case Share:
                return 9;
            default:
                return -1;
        }
    }
}
