package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.content.res.Configuration;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.Toast;

import nogu96.streetfighterthirdstrike.R;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    private int HIGH = 80, MEDIUM = 50, SMALL = 30;

    public CustomTextView(Context context, String text) {
        super(context);
        //al parecer no funcionan todos los elementos de un style al usarlo
        setText(text);

        switch(getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK){

            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                setLayoutParams(new LinearLayout.LayoutParams(HIGH, HIGH));
                setTextSize(20);
                break;

            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                this.setLayoutParams(new LinearLayout.LayoutParams(MEDIUM, MEDIUM));
                setTextSize(16);
                break;

            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                this.setLayoutParams(new LinearLayout.LayoutParams(SMALL, SMALL));
                setTextSize(14);
                break;

            default:
                this.setLayoutParams(new LinearLayout.LayoutParams(MEDIUM, MEDIUM));
                setTextSize(16);
                Toast.makeText(getContext(), "Unknow screen size", Toast.LENGTH_SHORT).show();
        }
        setGravity(Gravity.CENTER);
    }

}
