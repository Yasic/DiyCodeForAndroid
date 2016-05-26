package com.yasic.diycode.Presenter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yasic.diycode.View.BaseViewInterface;

/**
 * Created by ESIR on 2016/3/18.
 */
public abstract class BasePresenterFragment<BVI extends BaseViewInterface> extends Fragment {
    protected BVI BVIView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(getActivity());
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        try {
            BVIView = getBVIClass().newInstance();
            BVIView.init(inflater, container);
            onBindBVI();
            view = BVIView.getView();
        } catch (java.lang.InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public final void onDestroyView() {
        onDestroyBVI();
        BVIView = null;
        super.onDestroyView();
    }

    protected void onDestroyBVI() {};

    protected void onBindBVI(){};

    protected abstract Class<BVI> getBVIClass();
}
