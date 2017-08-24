package nogu96.streetfighterthirdstrike.model.pojo.character;

import java.io.Serializable;
import java.util.List;


public class Attacks implements Serializable{

    private List<Move> special_moves, super_arts, target_combos, command_moves;
    private String character;

    public List<Move> getSpecial_moves() {
        return special_moves;
    }

    public List<Move> getSuper_arts() {
        return super_arts;
    }

    public List<Move> getTarget_combos() {
        return target_combos;
    }

    public List<Move> getCommand_moves() {
        return command_moves;
    }

    public String getCharacterName() {
        return character;
    }
}
