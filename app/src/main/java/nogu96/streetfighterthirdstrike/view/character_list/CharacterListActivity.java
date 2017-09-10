package nogu96.streetfighterthirdstrike.view.character_list;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.dao.DAOAllCharacters;
import nogu96.streetfighterthirdstrike.view.character_detail.CharacterDetailActivity;

public class CharacterListActivity extends AppCompatActivity{

    private static final int COLUMN_NUMBER_RECYCLER = 2;

    private RecyclerView recyclerView;
    private AdapterCharacterList adapterCharacterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_list);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(ContextCompat.getColor(getApplicationContext(), R.color.white));
        setSupportActionBar(toolbar);

        //configuro el recyclerView
        recyclerView = (RecyclerView)findViewById(R.id.recycler_view_character_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), COLUMN_NUMBER_RECYCLER));
        adapterCharacterList = new AdapterCharacterList(getApplicationContext(), new DAOAllCharacters().getCharacterList(getApplicationContext()));
        adapterCharacterList.setListener(new CharacterListener());
        recyclerView.setAdapter(adapterCharacterList);
    }


    //listener del objeto del recyclerView
    private class CharacterListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), CharacterDetailActivity.class);
            intent.putExtra(CharacterDetailActivity.CHARACTER_KEY, adapterCharacterList.getCharacterAtPosition(recyclerView.getChildAdapterPosition(v)));
            startActivity(intent);
        }
    }

    //le asigno al actionbar los items con sus listeners
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_search_item, menu);

        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        //aplico que el texto sea blanco
        final EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint(R.string.search);
        searchEditText.setHintTextColor(getResources().getColor(R.color.secundaryText));

        //listener del texto del searchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override//cada vez que apreto enter
            public boolean onQueryTextSubmit(String query) {
                adapterCharacterList.searchForCharacter(query);
                return false;
            }

            @Override//cada vez que tipeo
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        //cuando el searchView es expandido
        MenuItemCompat.setOnActionExpandListener(myActionMenuItem, new MenuItemCompat.OnActionExpandListener() {
            @Override//cuando el searchView es expandido
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override//cuando el searchView es contraido
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapterCharacterList.setListToShow(new DAOAllCharacters().getCharacterList(getApplicationContext()));
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }


}
