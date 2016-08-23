package com.ziomacki.github.component;

import javax.inject.Inject;

public class TextUtils {

    @Inject
    public TextUtils() {}

    public boolean isNotEmpty(String string) {
        return string != null && !string.equals("");
    }
}
