package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import nogu96.streetfighterthirdstrike.R;

public class CustomView extends View{

    private int size = 100;

    public CustomView(Context context, String background) {
        super(context);
        this.setLayoutParams(new LinearLayout.LayoutParams(size, size));
        try {
            Class res = R.drawable.class;
            Field field = res.getField(background);
            this.setBackgroundResource( field.getInt(null));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

    }
}
