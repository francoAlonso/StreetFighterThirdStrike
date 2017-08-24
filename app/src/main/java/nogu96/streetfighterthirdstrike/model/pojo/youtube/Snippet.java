package nogu96.streetfighterthirdstrike.model.pojo.youtube;

public class Snippet {

    private String title;
    private ResourceId resourceId;
    private Thumbnails thumbnails;

    public String getTitle(){
        return  title;
    }

    public String getId(){
        return resourceId.getId();
    }

    public String getImageUrl(){
        return thumbnails.getImage();
    }

}
