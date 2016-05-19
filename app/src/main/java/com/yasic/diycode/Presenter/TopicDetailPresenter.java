package com.yasic.diycode.Presenter;

import com.yasic.diycode.View.TopicDetailView;

/**
 * Created by Yasic on 2016/5/19.
 */
public class TopicDetailPresenter extends BasePresenterActivity<TopicDetailView>{

    @Override
    protected Class<TopicDetailView> getBVIClass() {
        return TopicDetailView.class;
    }
}
