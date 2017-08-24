package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.pojo.character.Attacks;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterMovesFragment extends Fragment {

    public static final String ATTACKS_KEY = "attacks key";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_moves, container, false);

        Attacks attacks = (Attacks)getArguments().getSerializable(ATTACKS_KEY);
        //specialMoves
        RecyclerView recyclerSpecialMoves = (RecyclerView)view.findViewById(R.id.recycler_special_character_moves);
        recyclerSpecialMoves.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSpecialMoves.setAdapter(new AdapterMoveList(getContext(), attacks.getSpecial_moves()));
        //superArts
        RecyclerView recyclerSuperArts = (RecyclerView)view.findViewById(R.id.recycler_super_character_arts);
        recyclerSuperArts.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerSuperArts.setAdapter(new AdapterMoveList(getContext(), attacks.getSuper_arts()));
        //commandMoves
        RecyclerView recyclerCommandMoves = (RecyclerView)view.findViewById(R.id.recycler_command_character_moves);
        recyclerCommandMoves.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerCommandMoves.setAdapter(new AdapterMoveList(getContext(), attacks.getCommand_moves()));
        //targetCombos
        RecyclerView recyclerTargetCombos = (RecyclerView)view.findViewById(R.id.recycler_target_character_combos);
        recyclerTargetCombos.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerTargetCombos.setAdapter(new AdapterMoveList(getContext(), attacks.getTarget_combos()));

        return view;
    }

}
