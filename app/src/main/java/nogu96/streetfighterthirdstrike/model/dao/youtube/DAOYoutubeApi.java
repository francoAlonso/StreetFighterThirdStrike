package nogu96.streetfighterthirdstrike.model.dao.youtube;

import java.util.List;
import java.util.concurrent.ExecutionException;

import nogu96.streetfighterthirdstrike.internet.ResultListener;
import nogu96.streetfighterthirdstrike.model.pojo.youtube.Youtube;
import nogu96.streetfighterthirdstrike.model.pojo.youtube.YoutubeContainer;

public class DAOYoutubeApi {

    private static final String YOUTUBE_API_KEY = "AIzaSyCCgjOps8aBB5Htu2FNrxg4PN1o70eY6ao";
    private static final String HTTPS_PLAYLIST_ID = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=";

    private String getPlaylistUrl(String playlist_id){
        return HTTPS_PLAYLIST_ID + playlist_id + "&key=" + YOUTUBE_API_KEY;
    }

    public void getYoutubeList(String playlist_key, final ResultListener<List<Youtube>> controllerListener){
        Task<YoutubeContainer> task = new Task<>(YoutubeContainer.class, new ResultListener<YoutubeContainer>() {
            @Override
            public void finish(YoutubeContainer resultado) {
                try {
                    controllerListener.finish(resultado.getYoutubeList());
                }catch (Exception e){
                    controllerListener.finish(null);
                }
            }
        });

        task.execute(getPlaylistUrl(playlist_key));
    }

}
