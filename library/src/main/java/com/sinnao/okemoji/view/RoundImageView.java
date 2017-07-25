package com.sinnao.okemoji.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * 生成圆角图片
 * Created by liujigang on 2014/8/27 0027.
 */
public class RoundImageView extends AppCompatImageView {

    public RoundImageView(Context context) {
        super(context);
    }

    public RoundImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private Bitmap getCroppedRoundBitmap(Bitmap bmp, int radius) {
        int diameter = radius * 2;
        // 为了防止宽高不相等，造成圆形图片变形，因此截取长方形中处于中间位置最大的正方形图片
        int w = bmp.getWidth();
        int h = bmp.getHeight();
        //截取一个正方形
        if (h > w) {
            bmp = Bitmap.createBitmap(bmp, 0, (h - w) / 2, w, w);
        } else if (h < w) {
            bmp = Bitmap.createBitmap(bmp, (w - h) / 2, 0, h, h);
        }

        if (bmp.getWidth() != diameter) {
            bmp = Bitmap.createScaledBitmap(bmp, diameter, diameter, true);
        }
        Bitmap output = Bitmap.createBitmap(bmp.getWidth(), bmp.getHeight(), Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, bmp.getWidth(), bmp.getHeight());

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(radius, radius, radius, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bmp, rect, rect, paint);
        bmp.recycle();
        return output;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }
        int w = getWidth();
        int h = getHeight();
        if (w == 0 || h == 0) {
            return;
        }
        Path path = new Path();
        path.addCircle(getWidth()/2, getHeight()/2, getWidth()/2, Path.Direction.CCW);
        canvas.clipPath(path);
//        if (drawable instanceof BitmapDrawable) {
//            Bitmap b = ((BitmapDrawable) drawable).getBitmap();
//            Bitmap roundBitmap = getCroppedRoundBitmap(b.copy(Config.ARGB_8888, true), (w < h ? w : h) / 2);
//            canvas.drawBitmap(roundBitmap, 0, 0, null);
//            roundBitmap.recycle();
//        } else {
//            super.onDraw(canvas);
//        }
        drawable.draw(canvas);
    }

}
