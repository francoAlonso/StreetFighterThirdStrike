package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.pojo.character.Move;

public class CustomLinearLayout extends LinearLayout {

    private Context context;

    public CustomLinearLayout(Context context) {
        super(context);
        this.context = context;
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public CustomLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public void addMoves(List<Move> moveList){
        for (Move move : moveList){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.recycler_character_move_item, null);
            TextView txtMoveName = (TextView) view.findViewById(R.id.txt_move_name);
            LinearLayout linearMoves = (LinearLayout) view.findViewById(R.id.command_list_layout);

            txtMoveName.setText(move.getName());
            for (String command : move.getMoveList()){
                if(command.equals("far") || command.equals("close") || command.equals("air") || command.equals("hold")) {
                    linearMoves.addView(new CustomTextView(context, command));
                }else{
                    linearMoves.addView(new CustomView(context, command));
                }
            }

            addView(view);
        }
    }

}
