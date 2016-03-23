package com.ivollo.familychat;

import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/17 20:49
 */
public enum NavigationPage {
    TEST(R.layout.page_test, "测试页"),
    CONTACTS(R.layout.page_contacts, "联系人"),
    TIMES(R.layout.page_times, "时光");

    int id;
    String name;

    NavigationPage(@LayoutRes int id, String name) {
        this.id = id;
        this.name = name;
    }
}
