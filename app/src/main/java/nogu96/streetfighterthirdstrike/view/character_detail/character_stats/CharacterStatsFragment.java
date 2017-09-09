package nogu96.streetfighterthirdstrike.view.character_detail.character_stats;


import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.storage.StorageManager;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.OnScaleChangedListener;
import com.github.chrisbanes.photoview.PhotoView;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageException;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutionException;

import nogu96.streetfighterthirdstrike.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CharacterStatsFragment extends Fragment {

    public static final String STATS_KEY = "stats key";
    private OnFragmentInteractionListener fragmentListener;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_character_stats, container, false);

        final PhotoView photoView = (PhotoView)view.findViewById(R.id.character_stats_photo_view);

        StorageReference imageStorage =
                FirebaseStorage
                .getInstance()
                .getReferenceFromUrl("gs://street-fighter-third-strike.appspot.com")
                .child("character_stats")
                .child(getArguments().getString(STATS_KEY) + ".jpg");
        //traigo la imagen de firebase
        imageStorage.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //desaparesco el progressbar
                Glide.with(getContext())
                        .load(uri)
                        .into(photoView);
                view.findViewById(R.id.progress_bar_character_stats).setVisibility(View.GONE);
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //fragmentListener.tryAgain(new TryAgainFragment(), 1);
                if (e instanceof StorageException) {
                    Toast.makeText(getContext(), "image not found", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "error", Toast.LENGTH_SHORT).show();
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
