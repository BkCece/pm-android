package Utils;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;

import ca.sfu.pacmacro.R;

public class BitmapUtil {
    @NonNull
    public static Bitmap getBitmapFromDrawable(@NonNull Context context, int drawableResourceId) {
        int px = context.getResources().getDimensionPixelSize(R.dimen.character_map_icon_size);
        Bitmap mDotMarkerBitmap = Bitmap.createBitmap(px, px, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(mDotMarkerBitmap);
        Drawable shape = context.getResources().getDrawable(drawableResourceId);
        shape.setBounds(0, 0, mDotMarkerBitmap.getWidth(), mDotMarkerBitmap.getHeight());
        shape.draw(canvas);
        return mDotMarkerBitmap;
    }
}
