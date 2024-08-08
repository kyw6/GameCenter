package com.example.gamecenter.utils;

import android.content.Context;
import android.util.DisplayMetrics;

public class DpUtils {

    private Context context;

    // 构造函数
    public DpUtils(Context context) {
        this.context = context;
    }

    // 将 px 转换为 dp
    public int pxToDp(int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / displayMetrics.density);
    }

    // 将 dp 转换为 px
    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * displayMetrics.density);
    }
}
