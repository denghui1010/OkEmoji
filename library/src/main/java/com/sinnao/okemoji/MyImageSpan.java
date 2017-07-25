package com.sinnao.okemoji;

import android.graphics.drawable.Drawable;
import android.text.style.ImageSpan;

/**
 * Created by Liu Denghui on 2017/7/22.
 */

public class MyImageSpan extends ImageSpan {

    public MyImageSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

}
