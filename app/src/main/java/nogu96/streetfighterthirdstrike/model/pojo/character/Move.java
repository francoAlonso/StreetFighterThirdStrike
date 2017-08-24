package nogu96.streetfighterthirdstrike.model.pojo.character;

import java.io.Serializable;
import java.util.List;

public class Move implements Serializable{

    private List<String> moveList;
    private String name;

    public Move(String name, List<String> moveList) {
        this.moveList = moveList;
        this.name = name;
    }

    public List<String> getMoveList(){
        return moveList;
    }

    public String getName(){
        return name;
    }
}
