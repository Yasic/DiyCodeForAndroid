package com.yasic.diycode.Presenter;

import android.util.Log;
import android.widget.Toast;

import com.yasic.diycode.Bean.CallbackBean;
import com.yasic.diycode.Bean.TopicBean;
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
        getTopicList(1);
    }

    @Override
    protected Class<TopicListView> getBVIClass() {
        return TopicListView.class;
    }

    public Object getTopicList(final int page) {
        Observable.create(new Observable.OnSubscribe<CallbackBean<List<TopicBean>>>() {
            @Override
            public void call(Subscriber<? super CallbackBean<List<TopicBean>>> subscriber) {
                CallbackBean<List<TopicBean>> callbackBean = topicModel.getTopicList(1);
                subscriber.onNext(callbackBean);
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<CallbackBean<List<TopicBean>>>() {
                    @Override
                    public void call(CallbackBean<List<TopicBean>> callbackBean) {
                        if (callbackBean.getCode().equals("0")) {
                            BVIView.setRvTopicList(getActivity().getApplicationContext(), callbackBean.getResponse());
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
        return null;
    }
}
