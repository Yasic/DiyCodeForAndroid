package com.yasic.diycode.Presenter;

import android.support.v7.widget.Toolbar;

import com.yasic.diycode.R;
import com.yasic.diycode.View.ContainerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yasic on 2016/5/18.
 */
public class ContainerPresenter extends BasePresenterActivity<ContainerView> {
    @Override
    protected void onBindBVI() {
        setSupportActionBar((Toolbar) BVIView.getView().findViewById(R.id.tb_container));
        BVIView.setPresenter(this);
        List<String> tabTitleList = new ArrayList<>();
        tabTitleList.add("社区");
        tabTitleList.add("个人主页");
        tabTitleList.add("设置");
        List<BasePresenterFragment> basePresenterFragmentList = new ArrayList<>();
        basePresenterFragmentList.add(new TopicListPresenter());
        basePresenterFragmentList.add(new PersonalInfoPresenter());
        basePresenterFragmentList.add(new SettingPresenter());
        BVIView.setViewPagerAndTablayout(tabTitleList, basePresenterFragmentList);
    }


    @Override
    protected Class<ContainerView> getBVIClass() {
        return ContainerView.class;
    }
}
