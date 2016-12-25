package com.jack.utils;

import com.jack.main.R;
import com.nightonke.boommenu.BoomButtons.HamButton;

/**
 * Created by Administrator on 2016/12/23.
 */

public class BoomButtonUtils {
    private static int[] imageResources = new int[]{
            R.mipmap.icon_android,
            R.mipmap.bear,
            R.mipmap.bee,
            R.mipmap.bee

    };
    private static String[] menuText=new String[]{
      "Android",
       "IOS",
       "妹子" ,
        "java"
    };
    private static int imageResourceIndex = 0;

    static int getImageResource() {
        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
        return imageResources[imageResourceIndex++];
    }

   public static HamButton.Builder getHamButtonBuilder(int i) {
        return new HamButton.Builder()
                .normalImageRes(getImageResource())
                .normalText(menuText[i]).subTextSize(20);
    }
}
