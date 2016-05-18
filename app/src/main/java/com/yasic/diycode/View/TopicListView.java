package com.yasic.diycode.View;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.yasic.diycode.Adapter.TopicListAdapter;
import com.yasic.diycode.Bean.TopicItemBean;
import com.yasic.diycode.Presenter.TopicListPresenter;
import com.yasic.diycode.R;

import java.util.List;

/**
 * Created by Yasic on 2016/5/18.
 */
public class TopicListView implements BaseViewInterface<Activity, TopicListPresenter>{
    private View view;
    private RecyclerView rvTopicList;
    private ProgressBar prbTopicList;
    private TopicListPresenter topicListPresenter;
    @Override
    public void init(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.fragment_topiclist, container, false);
        rvTopicList = (RecyclerView) view.findViewById(R.id.rv_TopicList);
        prbTopicList = (ProgressBar) view.findViewById(R.id.prb_TopicList);
    }

    public void setRvTopicList(Context context, final List<TopicItemBean> topicItemBeanList){
        final TopicListAdapter topicListAdapter = new TopicListAdapter(context, topicItemBeanList);
        topicListAdapter.setOnItemClickListener(new TopicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                topicListPresenter.startTopicDetailPresenter(topicItemBeanList.get(position).getArticleSequence());
            }

            @Override
            public void onItemLongCick(View v, int position) {

            }
        });
        rvTopicList.setAdapter(topicListAdapter);
    }

    public void setProgressBarVisible(){
        prbTopicList.setVisibility(View.VISIBLE);
    }

    public void setProgressBarGone(){
        prbTopicList.setVisibility(View.GONE);
    }

    public void initRvTopicList(Context context){
        rvTopicList.setLayoutManager(new LinearLayoutManager(context));
        rvTopicList.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public void setPresenter(Activity activity) {

    }

    @Override
    public void setPresenter(TopicListPresenter topicListPresenter) {
        this.topicListPresenter = topicListPresenter;
    }
}
