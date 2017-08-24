package nogu96.streetfighterthirdstrike.view.character_detail.character_stats;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

import java.lang.reflect.Field;

import nogu96.streetfighterthirdstrike.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterStatsFragment extends Fragment {

    public static final String STATS_KEY = "stats key";
    private OnFragmentInteractionListener fragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_character_stats, container, false);

        PhotoView photoView = (PhotoView)view.findViewById(R.id.character_stats_photo_view);

        try {
            Class res = R.drawable.class;
            Field field = res.getField(getArguments().getString(STATS_KEY));
            photoView.setBackgroundResource(field.getInt(null));
        }catch (Exception e){
            Log.e("Error", e.toString());
        }

        photoView.setOnScaleChangeListener(new OnScaleChangedListener() {
            @Override
            public void onScaleChange(float scaleFactor, float focusX, float focusY) {
                fragmentListener.onScaleChange(scaleFactor);
            }
        });

        return view;
    }


    public interface OnFragmentInteractionListener{
        void onScaleChange(float scaleFactor);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            fragmentListener = (OnFragmentInteractionListener)context;
        }else{
            throw new RuntimeException(context.toString() + " must implement OnFragmentDetailListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentListener = null;
    }

}
