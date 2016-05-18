package com.yasic.diycode.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.yasic.diycode.Presenter.BasePresenterFragment;

import java.util.List;

/**
 * Created by ESIR on 2016/4/6.
 */
public class ViewPagerMainInterfaceAdapter<T extends BasePresenterFragment> extends FragmentPagerAdapter {
    private List<String> tabTitleList;
    private List<T> fragmentList;

    public ViewPagerMainInterfaceAdapter(FragmentManager fm, List<String> tabTitleList, List<T> fragmentList) {
        super(fm);
        this.tabTitleList = tabTitleList;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return tabTitleList == null ? 0 : tabTitleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitleList.get(position);
    }
}
