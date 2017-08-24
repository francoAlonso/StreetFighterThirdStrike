package nogu96.streetfighterthirdstrike.model.dao;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.view.character_detail.character_moves.CharacterMovesFragment;
import nogu96.streetfighterthirdstrike.view.character_detail.character_stats.CharacterStatsFragment;
import nogu96.streetfighterthirdstrike.view.character_detail.youtube_links.CharacterYoutubeFragment;

public class DAOCharacterDetailFragment {

    List<Fragment> fragmentList;

    //voy a generar los 3 fragments de cada detalle del personaje con cada bundle asignado.
    public List<Fragment> getFragmentList(Context context, Character character) {
        //character moves
        CharacterMovesFragment characterMovesFragment = new CharacterMovesFragment();
        Bundle bundleMoves = new Bundle();
        bundleMoves.putSerializable(CharacterMovesFragment.ATTACKS_KEY, new DAOAllCharacters().getAttack(context, character.getName()));
        characterMovesFragment.setArguments(bundleMoves);
        //character stats
        CharacterStatsFragment characterStatsFragment = new CharacterStatsFragment();
        Bundle bundleStats = new Bundle();
        bundleStats.putString(CharacterStatsFragment.STATS_KEY, character.getStats());
        characterStatsFragment.setArguments(bundleStats);
        //youtube
        CharacterYoutubeFragment youtubeFragment = new CharacterYoutubeFragment();
        Bundle bundleYoutube = new Bundle();
        bundleYoutube.putString(CharacterYoutubeFragment.YOUTUBE_KEY, character.getPlaylist_id());
        youtubeFragment.setArguments(bundleYoutube);

        fragmentList = new ArrayList<>();
        fragmentList.add(characterMovesFragment);
        fragmentList.add(characterStatsFragment);
        fragmentList.add(youtubeFragment);

        return fragmentList;
    }

}
