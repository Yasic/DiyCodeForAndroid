package com.yasic.diycode.View;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yasic on 2016/3/17.
 */
public interface BaseViewInterface<T extends Activity, E extends Fragment> {
    void init(LayoutInflater inflater, ViewGroup container);
    View getView();
    void setPresenter(T t);
    void setPresenter(E e);
}
