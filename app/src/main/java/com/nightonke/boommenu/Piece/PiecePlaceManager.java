package com.nightonke.boommenu.Piece;

import android.content.Context;
import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by Weiping Huang at 23:50 on 16/11/6
 * For Personal Open Source
 * Contact me at 2584541288@qq.com or nightonke@outlook.com
 * For more projects: https://github.com/Nightonke
 */

public class PiecePlaceManager {


    public static ArrayList<Point> getHamPositions(PiecePlaceEnum piecePlaceEnum,
                                            Point parentSize,
                                            int hamWidth,
                                            int hamHeight,
                                            int hamVerticalMargin) {
        ArrayList<Point> positions = new ArrayList<>();
        float w = hamWidth, h = hamHeight, vm = hamVerticalMargin;
        int half = piecePlaceEnum.pieceNumber() / 2;
        if (piecePlaceEnum.pieceNumber() % 2 == 0) {
            for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h / 2 - vm / 2 - i * (h + vm)));
            for (int i = 0; i < h; i++) positions.add(point(0, h / 2 + vm / 2 + i * (h + vm)));
        } else {
            for (int i = half - 1; i >= 0; i--) positions.add(point(0, -h - vm - i * (h + vm)));
            positions.add(point(0, 0));
            for (int i = 0; i < h; i++) positions.add(point(0, h + vm + i * (h + vm)));
        }
        for (Point point : positions) point.offset((int) (parentSize.x / 2 - w / 2), (int) (parentSize.y / 2 - h / 2));
        return positions;
    }



    public static BoomPiece createPiece(Context context,
                                 PiecePlaceEnum piecePlaceEnum,
                                 int color) {
        switch (piecePlaceEnum) {
            case HAM_1:
            case HAM_2:
            case HAM_3:
            case HAM_4:
            case HAM_5:
            case HAM_6:
                return createHam(context, color);
        }
        throw new RuntimeException("Unknown button-enum!");
    }


    private static Ham createHam(Context context, int color) {
        Ham ham = new Ham(context);
        ham.init(color);
        return ham;
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

    private static PiecePlaceManager ourInstance = new PiecePlaceManager();

    public static PiecePlaceManager getInstance() {
        return ourInstance;
    }

    private PiecePlaceManager() {
    }
}
