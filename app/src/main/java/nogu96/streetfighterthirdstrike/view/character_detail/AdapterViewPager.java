package nogu96.streetfighterthirdstrike.view.character_detail;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

import nogu96.streetfighterthirdstrike.R;

public class AdapterViewPager extends FragmentStatePagerAdapter{

    private List<Fragment> fragmentList;
    private String tabTitles[];

    public AdapterViewPager(Context context, FragmentManager fm, List<Fragment> fragmentList){
        super(fm);
        this.fragmentList = fragmentList;
        tabTitles = new String[] {
                context.getString(R.string.tab_moves),
                context.getString(R.string.tab_stats),
                context.getString(R.string.tab_videos)
        };
    }

    public void replace(Fragment fragment, int position){
        fragmentList.remove(position);
        fragmentList.add(position, fragment);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}
