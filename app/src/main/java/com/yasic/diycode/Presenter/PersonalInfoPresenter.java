package com.yasic.diycode.Presenter;

import com.yasic.diycode.View.PersonalInfoView;

/**
 * Created by Yasic on 2016/5/18.
 */
public class PersonalInfoPresenter extends BasePresenterFragment<PersonalInfoView> {
    @Override
    protected Class<PersonalInfoView> getBVIClass() {
        return PersonalInfoView.class;
    }
}
