package com.yasic.diycode.Presenter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import android.widget.Toast;

import com.yasic.diycode.Adapter.TopicDetailAdapter;
import com.yasic.diycode.Bean.CallbackBean;
import com.yasic.diycode.Bean.TopicDetailBean;
import com.yasic.diycode.Model.TopicModel;
import com.yasic.diycode.R;
import com.yasic.diycode.View.TopicDetailView;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Yasic on 2016/5/19.
 */
public class TopicDetailPresenter extends BasePresenterActivity<TopicDetailView>{
    private TopicModel topicModel = new TopicModel();
    private String SEQUENCE;
    private TopicDetailAdapter topicDetailAdapter;
    @Override
    protected void onBindBVI() {
        BVIView.setPresenter(this);
        BVIView.initRvTopicList(this);
        BVIView.getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BVIView.getView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                BVIView.setSwipeRefreshLayout(true);
            }
        });
        SEQUENCE = getIntent().getExtras().getString("SEQUENCE");
        getTopicDetail();
    }

    public void getTopicDetail() {
        Observable.create(new Observable.OnSubscribe<CallbackBean<TopicDetailBean>>() {
            @Override
            public void call(Subscriber<? super CallbackBean<TopicDetailBean>> subscriber) {
                CallbackBean<TopicDetailBean> callbackBean = topicModel.getTopicDetail(SEQUENCE);
                subscriber.onNext(callbackBean);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CallbackBean<TopicDetailBean>>() {
                    @Override
                    public void call(CallbackBean<TopicDetailBean> callbackBean) {
                        if (callbackBean.getCode().equals("0")) {
                            TopicDetailBean topicDetailBean = callbackBean.getResponse();
                            View topicDetailHeader = LayoutInflater.from(getApplicationContext())
                                    .inflate(R.layout.content_headoftopicdetail, BVIView.getRvTopicDetail(), false);
                            TextView tvTitle = (TextView) topicDetailHeader.findViewById(R.id.tv_Title);
                            TextView tvType = (TextView) topicDetailHeader.findViewById(R.id.tv_Type);
                            TextView tvAuthor = (TextView) topicDetailHeader.findViewById(R.id.tv_Author);
                            TextView tvTopicContent = (TextView) topicDetailHeader.findViewById(R.id.tv_TopicContent);
                            tvTitle.setText(topicDetailBean.getTitle());
                            tvType.setText(topicDetailBean.getType());
                            tvAuthor.setText(topicDetailBean.getAuthor());
                            tvTopicContent.setText(topicDetailBean.getArticle());
                            if (topicDetailAdapter == null){
                                topicDetailAdapter = new TopicDetailAdapter(getApplicationContext(), topicDetailBean);
                                BVIView.getRvTopicDetail().setAdapter(topicDetailAdapter);
                                topicDetailAdapter.setHeaderView(topicDetailHeader);
                            }
                            else {
                                topicDetailAdapter.refreshData(topicDetailBean);
                            }
                        } else {
                            if (callbackBean.getErrorMessage() == null || callbackBean.getErrorMessage().equals("")) {
                                Toast.makeText(getApplicationContext(), "貌似网络出现了错误？", Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(getApplicationContext(), callbackBean.getErrorMessage(), Toast.LENGTH_LONG).show();
                        }
                        BVIView.setSwipeRefreshLayout(false);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("throwable",throwable.getMessage());
                        BVIView.setSwipeRefreshLayout(false);
                    }
                });
    }

    @Override
    protected Class<TopicDetailView> getBVIClass() {
        return TopicDetailView.class;
    }

}
