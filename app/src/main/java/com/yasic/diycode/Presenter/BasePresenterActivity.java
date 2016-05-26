package com.yasic.diycode.Presenter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.yasic.diycode.View.BaseViewInterface;

/**
 * Created by Yasic on 2016/3/18.
 */
public abstract class BasePresenterActivity<BVI extends BaseViewInterface> extends AppCompatActivity {
    protected BVI BVIView;

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            BVIView = getBVIClass().newInstance();
            BVIView.init(getLayoutInflater(), null);
            Fresco.initialize(this);
            setContentView(BVIView.getView());
            onBindBVI();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected final void onDestroy() {
        onDestroyBVI();
        BVIView = null;
        super.onDestroy();
    }

    protected abstract Class<BVI> getBVIClass();

    protected void onBindBVI(){};

    protected void onDestroyBVI() {};
}
