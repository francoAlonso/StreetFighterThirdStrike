package nogu96.streetfighterthirdstrike.model.pojo.character;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AttacksContainer {

    @SerializedName("container")
    private List<Attacks> attacks;

    public List<Attacks> getAttackList(){
        return attacks;
    }

}
