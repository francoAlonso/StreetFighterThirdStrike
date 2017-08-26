package nogu96.streetfighterthirdstrike.view.character_list;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.view.character_detail.CharacterDetailActivity;

public class CharacterListActivity extends AppCompatActivity implements
    CharacterListFragment.OnFragmentInteraction{

    private static final String CHARACTER_LIST_TAG = "characterListTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_toolbar);

        setFragmentToShow(new CharacterListFragment(), CHARACTER_LIST_TAG);
    }

    private void setFragmentToShow(Fragment fragment, String TAG){
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.fragment_layout);

        if(currentFragment == null || TAG.equals(currentFragment.getTag())){

            if(currentFragment != null && currentFragment.getTag().equals(CHARACTER_LIST_TAG)){
                fragmentManager.popBackStack();
            }

            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_layout, fragment, TAG)
                    .commit();
        }
    }

    //viajo al detalle del character.
    @Override
    public void onSelectedCharacter(Character character) {
        Intent intent = new Intent(this, CharacterDetailActivity.class);
        intent.putExtra(CharacterDetailActivity.CHARACTER_KEY, character);
        startActivity(intent);
    }

    //le asigno al actionbar los items con sus listeners
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search_item, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        //aplico que el texto sea blanco
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint(R.string.search);
        searchEditText.setHintTextColor(getResources().getColor(R.color.gray));

        //listener del texto del searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override//cada vez que apreto enter
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override//cada vez que tipeo
            public boolean onQueryTextChange(String newText) {
                if (!newText.equals("")) {

                }
                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
}
