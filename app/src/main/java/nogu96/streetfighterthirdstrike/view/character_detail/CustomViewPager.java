package nogu96.streetfighterthirdstrike.view.character_detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;


public class CustomViewPager extends ViewPager {

    private boolean enable;

    public CustomViewPager(Context context) {
        super(context);
        enable = true;
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        enable = true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (enable) {
            return super.onTouchEvent(event);
        }

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (enable) {
            return super.onInterceptTouchEvent(event);
        }

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        enable = enabled;
    }

}
