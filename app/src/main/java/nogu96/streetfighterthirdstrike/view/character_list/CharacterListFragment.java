package nogu96.streetfighterthirdstrike.view.character_list;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.dao.DAOAllCharacters;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;

public class CharacterListFragment extends Fragment {

    private static final int COLUMN_NUMBER_RECYCLER = 2;

    private OnFragmentInteraction fragmentInteraction;
    private AdapterCharacterList adapterCharacterList;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_character_list, container, false);

        //configuro el recyclerView
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_view_character_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), COLUMN_NUMBER_RECYCLER));
        adapterCharacterList = new AdapterCharacterList(getContext(), new DAOAllCharacters().getCharacterList(getContext()));
        adapterCharacterList.setListener(new CharacterListener());
        recyclerView.setAdapter(adapterCharacterList);

        return view;
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnFragmentInteraction){
            fragmentInteraction = (OnFragmentInteraction) context;
        }else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentDetailListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        fragmentInteraction = null;
    }

    public interface OnFragmentInteraction{
        void onSelectedCharacter(Character character);
    }

    //listener del objeto del recyclerView
    private class CharacterListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            fragmentInteraction.onSelectedCharacter(adapterCharacterList.getCharacterAtPosition(recyclerView.getChildAdapterPosition(v)));
        }
    }

}
