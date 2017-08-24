package nogu96.streetfighterthirdstrike.model.pojo.youtube;

public class Youtube {

    private Snippet snippet;

    public String getVideoId(){
        return snippet.getId();
    }

    public String getImageUrl(){
        return snippet.getImageUrl();
    }

    public String getTitle(){
        return snippet.getTitle();
    }

}
