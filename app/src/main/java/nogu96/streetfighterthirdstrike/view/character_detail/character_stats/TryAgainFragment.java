package nogu96.streetfighterthirdstrike.view.character_detail.character_stats;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import nogu96.streetfighterthirdstrike.R;

public class TryAgainFragment extends Fragment {

    private static final
            String MESSAGE_KEY = "message",
            CHARACTER_KEY = "character",
            POSITION_KEY = "position";

    private OnFragmentInteractionListener listener;

    //fabrica de tryAgainFragment
    public static TryAgainFragment create(String message, String character, int position) {
        TryAgainFragment tryAgainFragment = new TryAgainFragment();
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE_KEY, message);
        bundle.putString(CHARACTER_KEY, character);
        bundle.putInt(POSITION_KEY, position);
        tryAgainFragment.setArguments(bundle);

        return tryAgainFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_try_again, container, false);

        TextView txtMessage = (TextView)view.findViewById(R.id.txt_try_again);
        txtMessage.setText(getArguments().getString(MESSAGE_KEY));

        view.findViewById(R.id.btn_try_again).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;
                Bundle bundle = new Bundle();
                switch (getArguments().getInt(POSITION_KEY)){
                    case 1://en el caso de statsFragment
                        fragment = new CharacterStatsFragment();
                        bundle.putString(CharacterStatsFragment.STATS_KEY, getArguments().getString(CHARACTER_KEY));
                        break;

                    case 2://en el caso de youtubeFragment

                        break;
                }
                bundle.putString(CharacterStatsFragment.STATS_KEY, getArguments().getString(CHARACTER_KEY));
                fragment.setArguments(bundle);
                listener.tryAgain(fragment, getArguments().getInt(POSITION_KEY));
            }
        });

        return view;
    }

    public interface OnFragmentInteractionListener {
        void tryAgain(Fragment fragment, int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener)context;
        }else{
            throw new RuntimeException(context.toString() + " must implement OnFragmentDetailListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

}
