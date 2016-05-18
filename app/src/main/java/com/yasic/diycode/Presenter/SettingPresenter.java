package com.yasic.diycode.Presenter;

import com.yasic.diycode.View.Settingview;

/**
 * Created by Yasic on 2016/5/18.
 */
public class SettingPresenter extends BasePresenterFragment<Settingview> {
    @Override
    protected Class<Settingview> getBVIClass() {
        return Settingview.class;
    }
}
