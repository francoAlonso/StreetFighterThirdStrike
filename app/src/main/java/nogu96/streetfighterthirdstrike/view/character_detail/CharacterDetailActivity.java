package nogu96.streetfighterthirdstrike.view.character_detail;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.dao.DAOCharacterDetailFragment;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.view.character_detail.character_stats.CharacterStatsFragment;

public class CharacterDetailActivity extends AppCompatActivity implements
        CharacterStatsFragment.OnFragmentInteractionListener{

    public static final String CHARACTER_KEY = "character key";

    private CustomViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_detail);

        Character character = (Character)getIntent().getExtras().getSerializable(CHARACTER_KEY);

        setTitle(character.getName());

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
    public void onScaleChange(float scaleFactor) {
        if(scaleFactor >= 1 && scaleFactor < 1.01){
            viewPager.setPagingEnabled(true);
        }else{
            viewPager.setPagingEnabled(false);
        }
    }

}
