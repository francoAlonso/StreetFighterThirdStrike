package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;

import nogu96.streetfighterthirdstrike.R;

public class CustomView extends View{

    private static final int SIZE_BIG = 70, SIZE_MEDIUM = 50, SIZE_SMALL = 30;

    public CustomView(Context context, String background) {
        super(context);

        //pregunto por el tamaño de la pantalla
        switch(getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK){

            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                this.setLayoutParams(new LinearLayout.LayoutParams(SIZE_BIG, SIZE_BIG));
                break;

            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                this.setLayoutParams(new LinearLayout.LayoutParams(SIZE_MEDIUM, SIZE_MEDIUM));
                break;

            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                this.setLayoutParams(new LinearLayout.LayoutParams(SIZE_SMALL, SIZE_SMALL));
                break;

            default:
                this.setLayoutParams(new LinearLayout.LayoutParams(SIZE_MEDIUM, SIZE_MEDIUM));
                Toast.makeText(getContext(), "Unknow screen size", Toast.LENGTH_SHORT).show();

        }

        //agrego la imagen
        try {
            Class res = R.drawable.class;
            Field field = res.getField(background);
            this.setBackgroundResource( field.getInt(null));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

    }
}
