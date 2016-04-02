package com.ivollo.familychat.commons.navigation;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.ivollo.familychat.R;
import com.ivollo.familychat.chat.ContactOperationActivity;
import com.ivollo.familychat.chat.ContactInvitationActivity;
import com.ivollo.familychat.login.LoginActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:导航控制器
 * <p/>
 * 首先在这里
 *
 * @author yining
 *         Created on 2016/4/1 13:31
 */
public enum Navigation {
    LOGIN(LoginActivity.class),
    TIMELINE(R.layout.page_test),//临时
    CONTACTS(R.layout.page_contacts),
    CONTACT_OPERATION(ContactOperationActivity.class),
    INVITATION(ContactInvitationActivity.class),
    TEST(R.layout.page_test);


    /**
     * 保存当前导航所在位置
     */
    public static ObservableField<Navigation> currentPage = new ObservableField<>(Navigation.CONTACTS);

    /**
     * 判断当前导航位置是否在指定位置(activity， layout page 或者 navigation)
     */
    public static boolean isAt(Navigation navigation) {
        return currentPage.get().equals(navigation);
    }

    public static boolean isAt(Class<Activity> activity) {
        return currentPage.get().isSame(activity);
    }

    public static boolean isAt(@LayoutRes int page) {
        return currentPage.get().isSame(page);
    }


    //导航目标有两种方式，一种是layout page切换，一种是activity切换。
    private Class targetClass = null;
    private int targetPage = -1;


    Navigation(Object idNavigationPage) {
        if (idNavigationPage instanceof Class) {
            targetClass = (Class) idNavigationPage;
        } else {
            targetPage = (Integer) idNavigationPage;
        }
    }

    //发出导航事件
    public void fire() {
        EventBus.getDefault().post(this);
    }

    //判断当前导航位置是否和指定的页面相同
    public boolean isSame(@LayoutRes int page) {
        return targetClass == null && page == targetPage;
    }

    //判断当前导航位置是否和指定的Activity相同
    public boolean isSame(Class targetClass) {
        return this.targetClass != null && this.targetClass.equals(targetClass);
    }

    /**
     * 执行页面跳转
     *
     * @param activity             当前activity
     * @param intent               启动目标activity所用的intent,主要是为了指定extras
     * @param requestCodeForResult 使用startActivityForResult时用的requestCode.为null时使用startActivity
     */
    public void go(Activity activity, @Nullable Intent intent, @Nullable Integer requestCodeForResult) {
        if (targetClass != null) {
            if (intent == null)
                intent = new Intent(activity, targetClass);
            if (requestCodeForResult == null) {
                activity.startActivity(intent);
            } else {
                activity.startActivityForResult(intent, requestCodeForResult);
            }
        }
        currentPage.set(this);
    }

    public void go(Activity activity, int requestCodeForResult) {
        go(activity, null, requestCodeForResult);
    }

    public void go(Activity activity) {
        go(activity, null, null);
    }

}
