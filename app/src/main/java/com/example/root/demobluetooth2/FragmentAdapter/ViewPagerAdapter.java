package com.example.root.demobluetooth2.FragmentAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by root on 29/04/2017.
 */


public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }
    @Override
    public Fragment getItem(int position) {
        Fragment frag=null;
        switch (position){
            case 0:
                frag = new FragmentChat();
                break;
            case 1:
                frag = new FragmentFriend();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
