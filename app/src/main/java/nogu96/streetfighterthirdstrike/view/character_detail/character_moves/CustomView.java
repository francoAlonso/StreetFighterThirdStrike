package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.util.Log;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import nogu96.streetfighterthirdstrike.R;

public class CustomView extends android.support.v7.widget.AppCompatImageView{

    private static final int SIZE = 80;

    public CustomView(Context context, String background) {
        super(context);
        this.setLayoutParams(new LinearLayout.LayoutParams(SIZE, SIZE));
        setScaleType(ScaleType.CENTER_CROP);
        //agrego la imagen
        try {
            Class res = R.drawable.class;
            Field field = res.getField(background);
            this.setImageResource( field.getInt(null));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

    }
}
