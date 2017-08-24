package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.view.Gravity;
import android.widget.LinearLayout;

import nogu96.streetfighterthirdstrike.R;

public class CustomTextView extends android.support.v7.widget.AppCompatTextView {

    private int dimens = 120;

    public CustomTextView(Context context, String text) {
        super(context);
        //al parecer no funcionan todos los elementos de un style al usarlo
        this.setText("("+text+")");
        this.setTextSize(20);
        this.setLayoutParams(new LinearLayout.LayoutParams(dimens, dimens));
        this.setGravity(Gravity.CENTER);
    }

}
