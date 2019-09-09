package com.pcf.customize.binding;

import android.content.Context;
import android.widget.Toast;

public class TaostUtils {

    static Context context;

    public static void init(Context context) {
        TaostUtils.context = context;
    }

    public static void show(String text) {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
