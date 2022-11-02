package com.example.educationapp.custom_fonts;

import android.content.Context;
import android.graphics.Typeface;

public class Utils {

    private static Typeface theBombTypeface;

    public static Typeface getTheBombTypeface(Context context) {
        if(theBombTypeface == null){
            theBombTypeface = Typeface.createFromAsset(context.getAssets(), "fonts/thebomb.ttf");
        }
        return theBombTypeface;
    }
}
