package nogu96.streetfighterthirdstrike.view.character_detail.character_stats;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.controller.Controller;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterStatsFragment extends Fragment{

    public static final String STATS_KEY = "stats key";
    private OnFragmentInteractionListener fragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_character_stats, container, false);

        final String characteStats = getArguments().getString(STATS_KEY);

        final PhotoView photoView = (PhotoView)view.findViewById(R.id.character_stats_photo_view);

        StorageReference imageStorage =
                FirebaseStorage
                .getInstance()
                .getReferenceFromUrl("gs://street-fighter-third-strike.appspot.com")
                .child("character_stats")
                .child(characteStats + ".jpg");

        //traigo la imagen de firebase
        imageStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(getContext())
                        .load(uri)
                        .into(photoView);
                //el progressbar tiene que desaparecer dps de traer la imagen de glide
                view.findViewById(R.id.progress_bar_character_stats).setVisibility(View.GONE);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                if (e instanceof StorageException) {
                    fragmentListener.tryAgain(TryAgainFragment.create(getString(R.string.image_not_found), characteStats, 1), 1);
                }else{
                    fragmentListener.tryAgain(TryAgainFragment.create(getString(R.string.unexpected_error), characteStats, 1), 1);
                }
            }
        });

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
        void tryAgain(Fragment fragment, int position);
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
