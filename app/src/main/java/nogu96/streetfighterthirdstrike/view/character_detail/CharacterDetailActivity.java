package nogu96.streetfighterthirdstrike.view.character_detail;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeStandalonePlayer;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.dao.DAOCharacterDetailFragment;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.view.character_detail.character_stats.CharacterStatsFragment;
import nogu96.streetfighterthirdstrike.view.character_detail.youtube_links.CharacterYoutubeFragment;

public class CharacterDetailActivity extends AppCompatActivity implements
        CharacterStatsFragment.OnFragmentInteractionListener,
        CharacterYoutubeFragment.OnFragmentInteraction{

    public static final String CHARACTER_KEY = "character key";

    private CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Character character = (Character)getIntent().getExtras().getSerializable(CHARACTER_KEY);

        setTitle(character.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (CustomViewPager) findViewById(R.id.view_pager_character_detail);
        viewPager.setAdapter(
                new AdapterViewPager(getApplicationContext(),
                getSupportFragmentManager(),
                new DAOCharacterDetailFragment().getFragmentList(this, character))
        );

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout_character_detail);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    @Override
    public void onScaleChange(float scaleFactor) {
        if(scaleFactor >= 1 && scaleFactor < 1.01){
            viewPager.setPagingEnabled(true);
        }else{
            viewPager.setPagingEnabled(false);
        }
    }

    @Override
    public void youtubePlayer(String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+videoId));
        intent.putExtra("force_fullscreen",true);
        startActivity(intent);
    }
}
