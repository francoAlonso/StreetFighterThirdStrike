package nogu96.streetfighterthirdstrike.controller;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import java.util.List;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.internet.ResultListener;
import nogu96.streetfighterthirdstrike.model.dao.youtube.DAOYoutubeApi;
import nogu96.streetfighterthirdstrike.model.pojo.youtube.Youtube;

public class Controller {

    public void getYoutubeList(Context context, String playlist_id, final ResultListener<List<Youtube>> viewListener){
        if (thereisInternet(context)){
            new DAOYoutubeApi().getYoutubeList(playlist_id, new ResultListener<List<Youtube>>() {
                @Override
                public void finish(List<Youtube> resultado) {
                    viewListener.finish(resultado);
                }
            });
        }else {
            Toast.makeText(context, R.string.internet_conection, Toast.LENGTH_SHORT).show();
        }
    }


    //verifica si hay acceso a internet
    private boolean thereisInternet(Context context){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
