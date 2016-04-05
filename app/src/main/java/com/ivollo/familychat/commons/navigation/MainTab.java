package com.ivollo.familychat.commons.navigation;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.support.annotation.LayoutRes;

import com.ivollo.familychat.R;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:
 *
 * @author yining
 *         Created on 2016/4/2 9:40
 */
public enum MainTab {
    CONTACTS(R.layout.page_contacts),
    TIMELINE(R.layout.page_test),//临时
    TEST(R.layout.page_test),
    SETTINGS(R.layout.page_test);

    ObservableInt bindableInt = new ObservableInt(2);


    public static ObservableField<MainTab> currentTab = new ObservableField<>(CONTACTS);
    private int idPage;

    MainTab(@LayoutRes int idNavigationPage) {
        idPage = idNavigationPage;
    }

    @LayoutRes
    public int getId() {
        return idPage;
    }

    public static void switchTab(MainTab tab) {
        if (currentTab.get().equals(tab))
            return;
        currentTab.set(tab);
    }

    public void fire() {
        switchTab(this);
    }


}
