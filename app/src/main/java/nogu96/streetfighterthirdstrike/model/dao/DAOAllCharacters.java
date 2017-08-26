package nogu96.streetfighterthirdstrike.model.dao;

import android.content.Context;
import android.content.res.AssetManager;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import nogu96.streetfighterthirdstrike.model.pojo.character.Attacks;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.model.pojo.character.CharacterContainer;

public class DAOAllCharacters {

    public List<Character> getCharacterList(Context context){
        List<Character> characterList = new ArrayList<>();
        //el try lo pide porque no siempre puede estar el archivo .json
        try{

            AssetManager manager = context.getAssets();//necesito del context para acceder a los archivos
            BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(manager.open("character.json")));// bufferedReader me permite leer un archivo json
            Gson gson = new Gson();
            CharacterContainer characterContainer = gson.fromJson(bufferReaderIn, CharacterContainer.class);
            characterList = characterContainer.getCharacterList();

        }catch (IOException e) {
            e.printStackTrace();
        }

        return characterList;
    }

    //recorre la lista de ataques y devuelve los ataques del personaje seleccionado
    public Attacks getAttack(Context context, String name){
        try{

            AssetManager manager = context.getAssets();//necesito del context para acceder a los archivos
            BufferedReader bufferReaderIn = new BufferedReader(new InputStreamReader(manager.open(name+".json")));// bufferedReader me permite leer un archivo json
            Gson gson = new Gson();
            return gson.fromJson(bufferReaderIn, Attacks.class);
        }catch (IOException e) {
            e.printStackTrace();
        }

        return new Attacks();
    }

}
