package nogu96.streetfighterthirdstrike.view.character_list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.dao.DAOAllCharacters;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;

public class AdapterCharacterList extends RecyclerView.Adapter implements View.OnClickListener{

    private List<Character> characterList;
    private View.OnClickListener listener;
    private LayoutInflater inflater;
    private Context context;

    public AdapterCharacterList(Context context, List<Character> characterList){
        this.context = context;
        this.characterList = characterList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_character_item, parent, false);
        view.setOnClickListener(listener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder)holder;
        viewHolder.loadData(characterList.get(position));
    }

    @Override
    public int getItemCount() {
        return characterList.size();
    }

    public void setListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null)
            listener.onClick(v);
    }

    public Character getCharacterAtPosition(int position){
        return characterList.get(position);
    }

    public void setListToShow(List<Character> characterList){
        this.characterList = characterList;
        notifyDataSetChanged();
    }

    public void searchForCharacter(String query){
        List<Character> newCharacterList = new ArrayList<>();

        for (Character character : new DAOAllCharacters().getCharacterList(context)) {
            if (character.getName().toLowerCase().contains(query)) {
                    newCharacterList.add(character);
            }
        }

        setListToShow(newCharacterList);
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imageView;
        private TextView txtName;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (CircleImageView) itemView.findViewById(R.id.image_character_item);
            txtName = (TextView)itemView.findViewById(R.id.text_view_character_item);
        }

        public void loadData(Character character){
            try{
                Class res = R.drawable.class;
                Field field = res.getField(character.getProfile_pic());
                imageView.setImageResource(field.getInt(null));
            }catch (Exception e){
                Log.e("error", e.toString());
            }

            txtName.setText(character.getName());
        }
    }
}
