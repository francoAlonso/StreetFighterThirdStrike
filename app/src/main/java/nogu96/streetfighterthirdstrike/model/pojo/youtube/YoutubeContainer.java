package nogu96.streetfighterthirdstrike.model.pojo.youtube;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YoutubeContainer {

    @SerializedName("items")
    private List<Youtube> youtubeList;

    public List<Youtube> getYoutubeList(){
        return youtubeList;
    }
}
