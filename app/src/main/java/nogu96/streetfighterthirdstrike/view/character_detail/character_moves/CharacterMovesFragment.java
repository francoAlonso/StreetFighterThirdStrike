package nogu96.streetfighterthirdstrike.view.character_detail.character_moves;


import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


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
        CustomLinearLayout linearSpecialMoves = (CustomLinearLayout) view.findViewById(R.id.linear_special_character_moves);
        linearSpecialMoves.addMoves(attacks.getSpecial_moves());
        //superArts
        CustomLinearLayout linearSuperArts = (CustomLinearLayout)view.findViewById(R.id.linear_super_character_arts);
        linearSuperArts.addMoves(attacks.getSuper_arts());
        //commandMoves
        CustomLinearLayout linearCommandMoves = (CustomLinearLayout)view.findViewById(R.id.linear_command_character_moves);
        linearCommandMoves.addMoves(attacks.getCommand_moves());
        //targetCombos
        CustomLinearLayout linearTargetCombos = (CustomLinearLayout)view.findViewById(R.id.linear_target_character_combos);
;       linearTargetCombos.addMoves(attacks.getTarget_combos());

        return view;
    }

}
