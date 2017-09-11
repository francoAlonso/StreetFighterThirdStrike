package nogu96.streetfighterthirdstrike.view.character_detail.youtube_links;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.controller.Controller;
import nogu96.streetfighterthirdstrike.internet.ResultListener;
import nogu96.streetfighterthirdstrike.model.pojo.youtube.Youtube;
import nogu96.streetfighterthirdstrike.view.character_detail.TryAgainFragment;

public class CharacterYoutubeFragment extends Fragment {

    public static final String YOUTUBE_KEY = "youtube key";
    private static final int POSITION = 2;

    private AdapterYoutube adapterYoutube;
    private RecyclerView recyclerView;
    private OnFragmentInteraction fragmentInteraction;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_character_youtube, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_character_youtube);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Controller controller = new Controller();

        final String youtubeKey = getArguments().getString(YOUTUBE_KEY);

        controller.getYoutubeList(getContext(), youtubeKey, new ResultListener<List<Youtube>>() {
            @Override
            public void finish(List<Youtube> resultado) {
                if(resultado != null) {
                    adapterYoutube = new AdapterYoutube(getContext(), resultado);
                    adapterYoutube.setListener(new YoutubeListener());
                    recyclerView.setAdapter(adapterYoutube);
                    //hay que sacar el progress bar
                    view.findViewById(R.id.progress_bar_character_youtube).setVisibility(View.GONE);
                }else{
                    fragmentInteraction.tryAgain(TryAgainFragment.create(getString(R.string.unexpected_error), youtubeKey, POSITION), POSITION);
                }
            }
        });

        
        return view;
    }

    public interface OnFragmentInteraction{
        void youtubePlayer(String videoId);
        void tryAgain(Fragment fragment, int position);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteraction) {
            fragmentInteraction = (OnFragmentInteraction) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentDetailListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentInteraction = null;
    }

    private class YoutubeListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Youtube youtube = adapterYoutube.getYoutubeAtPosition(recyclerView.getChildAdapterPosition(v));
            fragmentInteraction.youtubePlayer(youtube.getVideoId());
        }
    }

}
