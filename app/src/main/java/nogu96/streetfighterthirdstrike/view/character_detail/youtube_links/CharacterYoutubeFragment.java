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
import nogu96.streetfighterthirdstrike.view.character_list.CharacterListFragment;

public class CharacterYoutubeFragment extends Fragment {

    public static final String YOUTUBE_KEY = "youtube key";

    private AdapterYoutube adapterYoutube;
    private RecyclerView recyclerView;
    private OnFragmentInteraction fragmentInteraction;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_youtube, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_character_youtube);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        new Controller().getYoutubeList(getContext(), getArguments().getString(YOUTUBE_KEY), new ResultListener<List<Youtube>>() {
            @Override
            public void finish(List<Youtube> resultado) {
                adapterYoutube = new AdapterYoutube(getContext(), resultado);
                adapterYoutube.setListener(new YoutubeListener());
                recyclerView.setAdapter(adapterYoutube);
            }
        });

        return view;
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

    public interface OnFragmentInteraction{
        void youtubePlayer(String videoId);
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
