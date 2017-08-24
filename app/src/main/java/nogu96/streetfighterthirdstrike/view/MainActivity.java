package nogu96.streetfighterthirdstrike.view;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import nogu96.streetfighterthirdstrike.R;
import nogu96.streetfighterthirdstrike.model.pojo.character.Character;
import nogu96.streetfighterthirdstrike.view.character_detail.CharacterDetailActivity;
import nogu96.streetfighterthirdstrike.view.character_list.CharacterListFragment;

public class MainActivity extends AppCompatActivity implements
    CharacterListFragment.OnFragmentInteraction{

    private static final String CHARACTER_LIST_TAG = "characterListTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
