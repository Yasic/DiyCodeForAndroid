package com.yasic.diycode.View;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yasic.diycode.Presenter.TopicDetailPresenter;
import com.yasic.diycode.R;

/**
 * Created by Yasic on 2016/5/19.
 */
public class TopicDetailView implements BaseViewInterface<TopicDetailPresenter, Fragment> {
    private View view;
    private TopicDetailPresenter topicDetailPresenter;
    private RecyclerView rvTopicDetail;
    private SwipeRefreshLayout swipeRefreshLayout;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.activity_topicdetail, container, false);
        rvTopicDetail = (RecyclerView) view.findViewById(R.id.rv_TopicDetail);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.srl_RefreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    public void setSwipeRefreshLayout(boolean isRefreshing){
        swipeRefreshLayout.setRefreshing(isRefreshing);
    }

    public void initRvTopicList(Context context){
       rvTopicDetail.setLayoutManager(new LinearLayoutManager(context));
       rvTopicDetail.setItemAnimator(new DefaultItemAnimator());
    }

    public RecyclerView getRvTopicDetail(){
        return rvTopicDetail;
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
