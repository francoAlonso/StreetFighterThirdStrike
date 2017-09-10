package nogu96.streetfighterthirdstrike.view.character_detail;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.dao.DAOCharacterDetailFragment;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.view.character_detail.character_stats.CharacterStatsFragment;
import nogu96.streetfighterthirdstrike.view.character_detail.character_stats.TryAgainFragment;
import nogu96.streetfighterthirdstrike.view.character_detail.youtube_links.CharacterYoutubeFragment;

public class CharacterDetailActivity extends AppCompatActivity implements
        CharacterStatsFragment.OnFragmentInteractionListener,
        CharacterYoutubeFragment.OnFragmentInteraction,
        TryAgainFragment.OnFragmentInteractionListener{

    public static final String CHARACTER_KEY = "character key";

    private CustomViewPager viewPager;
    private AdapterViewPager adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Character character = (Character)getIntent().getExtras().getSerializable(CHARACTER_KEY);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(character.getName());

        viewPager = (CustomViewPager) findViewById(R.id.view_pager_character_detail);
        adapterViewPager =  new AdapterViewPager(
                getApplicationContext(),
                getSupportFragmentManager(),
                new DAOCharacterDetailFragment().getFragmentList(this, character));
        viewPager.setAdapter(adapterViewPager);

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

    //cuando falla la conexion a internet. El tryAgainFragment y el CharacterStatsFragment comparten esta interfaz
    @Override
    public void tryAgain(Fragment fragment, int position) {
        adapterViewPager.replace(fragment, position);
    }

    @Override
    public void youtubePlayer(String videoId) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+videoId));
        intent.putExtra("force_fullscreen",true);
        startActivity(intent);
    }
}
