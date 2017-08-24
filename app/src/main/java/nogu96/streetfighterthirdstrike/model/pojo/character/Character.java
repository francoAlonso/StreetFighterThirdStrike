package nogu96.streetfighterthirdstrike.model.pojo.character;

import java.io.Serializable;

public class Character implements Serializable {

    private String name, profile_pic, stats, playlist_id;

    public String getName() {
        return name;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public String getStats(){
        return stats;
    }

    public String getPlaylist_id(){
        return playlist_id;
    }

}
