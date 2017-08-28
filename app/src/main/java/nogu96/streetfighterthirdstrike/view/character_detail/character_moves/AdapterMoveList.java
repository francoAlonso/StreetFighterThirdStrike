package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.pojo.character.Move;


public class AdapterMoveList extends RecyclerView.Adapter {

    private List<Move> moveList;
    private Context context;

    public AdapterMoveList(Context context, List<Move> moveList){
        this.moveList = moveList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_character_move_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.loadData(moveList.get(position));
    }

    @Override
    public int getItemCount() {
        return moveList.size();
    }


    private class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtName;
        LinearLayout linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            txtName = (TextView)itemView.findViewById(R.id.txt_move_name);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.command_list_layout);
        }

        public void loadData(Move move){
            txtName.setText(move.getName());
            //genero de manera dinamica los commando para el movimiento
            for (String command : move.getMoveList()){
                if(command.equals("far") || command.equals("close") || command.equals("air") || command.equals("hold")) {
                    linearLayout.addView(new CustomTextView(context, command));
                }else{
                    linearLayout.addView(new CustomView(context, command));
                }
            }
        }
    }
}
