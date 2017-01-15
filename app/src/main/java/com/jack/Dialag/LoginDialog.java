package com.jack.Dialag;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.jack.main.R;

/**
 * Created by jack on 2017/1/9.
 */

public class LoginDialog extends Dialog {
    private static int default_width = 160; //默认宽度
    private static int default_height = 120;//默认高度
    public LoginDialog(Context context) {
        this(context,0);
    }

    public LoginDialog(Context context, int themeResId) {
        super(context, themeResId);
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.diaog_login);


    }
}
