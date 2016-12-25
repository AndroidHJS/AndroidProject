package com.nightonke.boommenu.BoomButtons;

import android.graphics.Point;

import com.nightonke.boommenu.Util;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 20:26 on 16/11/19
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class ButtonPlaceManager {

    public static ArrayList<Point> getCircleButtonPositions(ButtonPlaceEnum placeEnum,
                                                            ButtonPlaceAlignmentEnum alignmentEnum,
                                                            Point parentSize,
                                                            float radius,
                                                            int buttonNumber,
                                                            float buttonHorizontalMargin,
                                                            float buttonVerticalMargin,
                                                            float buttonInclinedMargin,
                                                            float buttonTopMargin,
                                                            float buttonBottomMargin,
                                                            float buttonLeftMargin,
                                                            float buttonRightMargin) {

        ArrayList<Point> positions = new ArrayList<>(buttonNumber);
        float a, b, c, e, r = radius;
        float hm = buttonHorizontalMargin, vm = buttonVerticalMargin, im = buttonInclinedMargin;
        int h = buttonNumber / 2;

        switch (placeEnum) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(-r - hm / 2 - i * (2 * r + hm), 0));
                    for (int i = 0; i < h; i++) positions.add(point(r + hm / 2 + i * (2 * r + hm), 0));
                } else {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(-2 * r - hm - i * (2 * r + hm), 0));
                    positions.add(point(0, 0));
                    for (int i = 0; i < h; i++) positions.add(point(2 * r + hm + i * (2 * r + hm), 0));
                }
                break;
            case Vertical:
                if (buttonNumber % 2 == 0) {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(0, -r - vm / 2 - i * (2 * r + vm)));
                    for (int i = 0; i < h; i++) positions.add(point(0, r + vm / 2 + i * (2 * r + vm)));
                } else {
                    for (int i = h - 1; i >= 0; i--) positions.add(point(0, -2 * r - vm - i * (2 * r + vm)));
                    positions.add(point(0, 0));
                    for (int i = 0; i < h; i++) positions.add(point(0, 2 * r + vm + i * (2 * r + vm)));
                }
                break;
        }

        calculatePositionsInParent(positions, parentSize);

        calculateOffset(
                positions,
                alignmentEnum,
                parentSize,
                radius * 2,
                radius * 2,
                buttonTopMargin,
                buttonBottomMargin,
                buttonLeftMargin,
                buttonRightMargin);

        return positions;
    }

    public static ArrayList<Point> getCircleButtonPositions(ButtonPlaceEnum placeEnum,
                                                            ButtonPlaceAlignmentEnum alignmentEnum,
                                                            Point parentSize,
                                                            float buttonWidth,
                                                            float buttonHeight,
                                                            int buttonNumber,
                                                            float buttonHorizontalMargin,
                                                            float buttonVerticalMargin,
                                                            float buttonInclinedMargin,
                                                            float buttonTopMargin,
                                                            float buttonBottomMargin,
                                                            float buttonLeftMargin,
                                                            float buttonRightMargin) {

        ArrayList<Point> positions = new ArrayList<>(buttonNumber);
        float a, b, c, e, w = buttonWidth, h = buttonHeight;
        float hm = buttonHorizontalMargin, vm = buttonVerticalMargin, im = buttonInclinedMargin;
        int half = buttonNumber / 2;

        switch (placeEnum) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(-w / 2 - hm / 2 - i * (w + hm), 0));
                    for (int i = 0; i < half; i++) positions.add(point(w / 2  + hm / 2 + i * (w + hm), 0));
                } else {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(-w - hm - i * (w + hm), 0));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(w + hm + i * (w + hm), 0));
                }
                break;
            case Vertical:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h / 2 - vm / 2 - i * (h + vm)));
                    for (int i = 0; i < half; i++) positions.add(point(0, h / 2 + vm / 2 + i * (h + vm)));
                } else {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h - vm - i * (h + vm)));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(0, h + vm + i * (h + vm)));
                }
                break;


        }

        calculatePositionsInParent(positions, parentSize);

        calculateOffset(
                positions,
                alignmentEnum,
                parentSize,
                w,
                h,
                buttonTopMargin,
                buttonBottomMargin,
                buttonLeftMargin,
                buttonRightMargin);

        return positions;
    }

    public static ArrayList<Point> getHamButtonPositions(ButtonPlaceEnum placeEnum,
                                                         ButtonPlaceAlignmentEnum alignmentEnum,
                                                         Point parentSize,
                                                         float buttonWidth,
                                                         float buttonHeight,
                                                         int buttonNumber,
                                                         float buttonHorizontalMargin,
                                                         float buttonVerticalMargin,
                                                         float buttonTopMargin,
                                                         float buttonBottomMargin,
                                                         float buttonLeftMargin,
                                                         float buttonRightMargin,
                                                         float bottomHamButtonTopMargin) {
        ArrayList<Point> positions = new ArrayList<>(buttonNumber);
        float w = buttonWidth, h = buttonHeight;
        float hm = buttonHorizontalMargin, vm = buttonVerticalMargin;
        int half = buttonNumber / 2;

        switch (placeEnum) {
            case Horizontal:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--)
                        positions.add(point(-w / 2 - hm / 2 - i * (w + hm), 0));
                    for (int i = 0; i < half; i++)
                        positions.add(point(w / 2 + hm / 2 + i * (w + hm), 0));
                } else {
                    for (int i = half - 1; i >= 0; i--)
                        positions.add(point(-w - hm - i * (w + hm), 0));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(w + hm + i * (w + hm), 0));
                }
                break;
            case Vertical:
            case HAM_1:
            case HAM_2:
            case HAM_3:
            case HAM_4:
            case HAM_5:
            case HAM_6:
                if (buttonNumber % 2 == 0) {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h / 2 - vm / 2 - i * (h + vm)));
                    for (int i = 0; i < half; i++) positions.add(point(0, h / 2 + vm / 2 + i * (h + vm)));
                } else {
                    for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h - vm - i * (h + vm)));
                    positions.add(point(0, 0));
                    for (int i = 0; i < half; i++) positions.add(point(0, h + vm + i * (h + vm)));
                }
                if (buttonNumber >= 2 && bottomHamButtonTopMargin != Util.dp2px(-1))
                    positions.get(positions.size() - 1).offset(0, (int) (bottomHamButtonTopMargin - vm));
                break;
        }

        calculatePositionsInParent(positions, parentSize);

        calculateOffset(
                positions,
                alignmentEnum,
                parentSize,
                w,
                h,
                buttonTopMargin,
                buttonBottomMargin,
                buttonLeftMargin,
                buttonRightMargin);

        return positions;
    }

    private static void calculatePositionsInParent(ArrayList<Point> positions,
                                                   Point parentSize) {
        for (int i = 0; i < positions.size(); i++) {
            Point point = positions.get(i);
            positions.set(i, new Point(
                    (int) (point.x + parentSize.x / 2.0),
                    (int) (point.y + parentSize.y / 2.0)));
        }
    }

    private static void calculateOffset(ArrayList<Point> positions,
                                        ButtonPlaceAlignmentEnum alignmentEnum,
                                        Point parentSize,
                                        float width,
                                        float height,
                                        float buttonTopMargin,
                                        float buttonBottomMargin,
                                        float buttonLeftMargin,
                                        float buttonRightMargin) {
        int minHeight = Integer.MAX_VALUE;
        int maxHeight = Integer.MIN_VALUE;
        int minWidth = Integer.MAX_VALUE;
        int maxWidth = Integer.MIN_VALUE;
        Point offset = new Point(0, 0);

        for (Point position : positions) {
            maxHeight = Math.max(maxHeight, position.y);
            minHeight = Math.min(minHeight, position.y);
            maxWidth = Math.max(maxWidth, position.x);
            minWidth = Math.min(minWidth, position.x);
        }

        switch (alignmentEnum) {
            case Center:
                break;
            case Top:
                offset.y = (int) (height / 2 + buttonTopMargin - minHeight);
                break;
            case Bottom:
                offset.y = (int) (parentSize.y - height / 2 - maxHeight - buttonBottomMargin);
                break;
            case Left:
                offset.x = (int) (width / 2 + buttonLeftMargin - minWidth);
                break;
            case Right:
                offset.x = (int) (parentSize.x - width / 2 - maxWidth - buttonRightMargin);
                break;
            case TL:
                offset.y = (int) (height / 2 + buttonTopMargin - minHeight);
                offset.x = (int) (width / 2 + buttonLeftMargin - minWidth);
                break;
            case TR:
                offset.y = (int) (height / 2 + buttonTopMargin - minHeight);
                offset.x = (int) (parentSize.x - width / 2 - maxWidth - buttonRightMargin);
                break;
            case BL:
                offset.y = (int) (parentSize.y - height / 2 - maxHeight - buttonBottomMargin);
                offset.x = (int) (width / 2 + buttonLeftMargin - minWidth);
                break;
            case BR:
                offset.y = (int) (parentSize.y - height / 2 - maxHeight - buttonBottomMargin);
                offset.x = (int) (parentSize.x - width / 2 - maxWidth - buttonRightMargin);
                break;
            case Unknown:
                throw new RuntimeException("Unknown button-place-alignment-enum!");
        }

        for (int i = 0; i < positions.size(); i++) {
            Point position = positions.get(i);
            positions.set(i, new Point(position.x + offset.x, position.y + offset.y));
        }
    }

    private static Point point(float x, float y) {
        return new Point((int) x, (int) y);
    }

    private static Point point(double x, double y) {
        return new Point((int) x, (int) y);
    }

    private static Point point(int x, int y) {
        return new Point(x, y);
    }

    private static ButtonPlaceManager ourInstance = new ButtonPlaceManager();

    public static ButtonPlaceManager getInstance() {
        return ourInstance;
    }

    private ButtonPlaceManager() {
    }
}
