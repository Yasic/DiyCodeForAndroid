package com.yasic.diycode.Presenter;

import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.yasic.diycode.Bean.CallbackBean;
import com.yasic.diycode.Bean.TopicDetailBean;
import com.yasic.diycode.Bean.TopicItemBean;
import com.yasic.diycode.Model.TopicModel;
import com.yasic.diycode.View.TopicListView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by Yasic on 2016/5/18.
 */
public class TopicListPresenter extends BasePresenterFragment<TopicListView>{
    private TopicModel topicModel = new TopicModel();
    @Override
    protected void onBindBVI() {
        BVIView.setPresenter(this);
        BVIView.initRvTopicList(getActivity().getApplicationContext());
        BVIView.getView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                BVIView.getView().getViewTreeObserver().removeGlobalOnLayoutListener(this);
                BVIView.setProgressBarVisible();
            }
        });
        getTopicList(1);
    }

    public Object getTopicList(final int page) {
        Observable.create(new Observable.OnSubscribe<CallbackBean<List<TopicItemBean>>>() {
            @Override
            public void call(Subscriber<? super CallbackBean<List<TopicItemBean>>> subscriber) {
                CallbackBean<List<TopicItemBean>> callbackBean = topicModel.getTopicList(1+"");
                subscriber.onNext(callbackBean);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CallbackBean<List<TopicItemBean>>>() {
                    @Override
                    public void call(CallbackBean<List<TopicItemBean>> callbackBean) {
                        if (callbackBean.getCode().equals("0")) {
                            BVIView.setRvTopicList(getActivity().getApplicationContext(), callbackBean.getResponse());
                        } else {
                            if (callbackBean.getErrorMessage() == null || callbackBean.getErrorMessage().equals("")) {
                                Toast.makeText(getContext(), "貌似网络出现了错误？", Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(getContext(), callbackBean.getErrorMessage(), Toast.LENGTH_LONG).show();
                        }
                        BVIView.setProgressBarGone();
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("throwable",throwable.getMessage());
                        BVIView.setProgressBarGone();
                    }
                });
        return null;
    }

    public void startTopicDetailPresenter(final String Sequence){
        Observable.create(new Observable.OnSubscribe<CallbackBean<List<TopicDetailBean>>>() {
            @Override
            public void call(Subscriber<? super CallbackBean<List<TopicDetailBean>>> subscriber) {
                CallbackBean<List<TopicDetailBean>> callbackBean = topicModel.getTopicDetail(Sequence);
                subscriber.onNext(callbackBean);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CallbackBean<List<TopicDetailBean>>>() {
                    @Override
                    public void call(CallbackBean<List<TopicDetailBean>> callbackBean) {
                        if (callbackBean.getCode().equals("0")) {
                        } else {
                            if (callbackBean.getErrorMessage() == null || callbackBean.getErrorMessage().equals("")) {
                                Toast.makeText(getContext(), "貌似网络出现了错误？", Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(getContext(), callbackBean.getErrorMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.i("throwable",throwable.getMessage());
                    }
                });
    }

    @Override
    protected Class<TopicListView> getBVIClass() {
        return TopicListView.class;
    }
}
