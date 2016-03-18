package com.ivollo.familychat;

import android.databinding.ObservableInt;
import android.view.View;

import com.ivollo.familychat.navigation.NavigateToLoginEvent;

import org.greenrobot.eventbus.EventBus;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/17 20:46
 */
public class Navigator {

    public ObservableInt idCurrentPage = new ObservableInt(R.layout.page_test);

    public void navigate(View v) {
        NavigationPage page;
        switch (v.getId()) {
            case R.id.navigate_contact:
                page = NavigationPage.CONTACTS;
                break;
            case R.id.navigate_test:
                page = NavigationPage.TEST;
                break;
            case R.id.navigate_login:
                EventBus.getDefault().post(new NavigateToLoginEvent());
                return;
            default:
                return;
        }
        if (idCurrentPage.get() == page.id)
            return;
        idCurrentPage.set(page.id);
    }


}

