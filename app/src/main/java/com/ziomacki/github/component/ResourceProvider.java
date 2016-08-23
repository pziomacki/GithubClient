package com.ziomacki.github.component;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class ResourceProvider {

    private Context context;

    public ResourceProvider(Context context) {
        this.context = context;
    }

    public Drawable getDrawable(int drawableID) {
        return context.getDrawable(drawableID);
    }

}
