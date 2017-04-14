package test.bwie.com.jinrtt.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import test.bwie.com.jinrtt.UserFragment;


public class MyPagerAdapter extends FragmentPagerAdapter {

    private String [] tablist;
    private String [] pathlist;

    public MyPagerAdapter(FragmentManager fm, String[] tablist, String[] pathlist) {
        super(fm);
        this.tablist = tablist;
        this.pathlist = pathlist;
    }

    @Override
    public Fragment getItem(int position) {
        UserFragment myFragment = new UserFragment();
        myFragment.category=pathlist[position];
        return myFragment;
    }

    @Override
    public int getCount() {
        return pathlist.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist[position];
    }

}
