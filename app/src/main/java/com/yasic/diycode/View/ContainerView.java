package com.yasic.diycode.View;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasic.diycode.Adapter.ViewPagerMainInterfaceAdapter;
import com.yasic.diycode.Presenter.BasePresenterFragment;
import com.yasic.diycode.Presenter.ContainerPresenter;
import com.yasic.diycode.R;

import java.util.List;

/**
 * Created by Yasic on 2016/5/18.
 */
public class ContainerView implements BaseViewInterface<ContainerPresenter, Fragment> {
    private View view;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private ContainerPresenter containerPresenter;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_container, container, false);
        viewPager = (ViewPager) view.findViewById(R.id.vp_Container);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_Container);
    }

    @Override
    public View getView() {
        return view;
    }

    public void setViewPagerAndTablayout(List<String> tabTitleList, List<BasePresenterFragment> basePresenterFragmentList) {
        viewPager.setAdapter(new ViewPagerMainInterfaceAdapter<>(
                containerPresenter.getSupportFragmentManager(),
                tabTitleList,
                basePresenterFragmentList));
        viewPager.setOffscreenPageLimit(3);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        containerPresenter.getSupportFragmentManager().beginTransaction().commit();
    }

    @Override
    public void setPresenter(ContainerPresenter containerPresenter) {
        this.containerPresenter = containerPresenter;
    }

    @Override
    public void setPresenter(Fragment fragment) {

    }
}
