package com.yasic.diycode.View;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasic.diycode.Presenter.TopicDetailPresenter;

/**
 * Created by Yasic on 2016/5/19.
 */
public class TopicDetailView implements BaseViewInterface<TopicDetailPresenter, Fragment> {
    private View view;
    private TopicDetailPresenter topicDetailPresenter;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {

    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(TopicDetailPresenter topicDetailPresenter) {
        this.topicDetailPresenter = topicDetailPresenter;
    }

    @Override
    public void setPresenter(Fragment fragment) {

    }
}
