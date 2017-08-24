package nogu96.streetfighterthirdstrike.model.pojo.character;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterContainer {
    @SerializedName("characters")
    List<Character> characterList;

    public List<Character> getCharacterList(){
        return characterList;
    }

}
