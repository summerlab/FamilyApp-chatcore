package com.ivollo.familychat.commons.navigation;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ivollo.familychat.MainActivity;
import com.ivollo.familychat.chat.contact.ContactOperationActivity;
import com.ivollo.familychat.chat.contact.ContactInvitationActivity;
import com.ivollo.familychat.chat.conversation.ConversationActivity;
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
    MAIN(MainActivity.class),
    LOGIN(LoginActivity.class),
    CONVERSATION(ConversationActivity.class),
    CONTACT_OPERATION(ContactOperationActivity.class),
    INVITATION(ContactInvitationActivity.class);


    /**
     * 保存当前导航所在位置
     */
    //public static ObservableField<Navigation> currentPage = new ObservableField<>(Navigation.MAIN);
    //public static ObservableField<Navigation> currentActivity = new ObservableField<>(Navigation.MAIN);

    /**
     * 判断当前导航位置是否在指定位置(activity， layout page 或者 navigation)
     */
//    public static boolean isAt(Navigation navigation) {
//        return currentPage.get().equals(navigation);
//    }
//
//    public static boolean isAt(Class<Activity> activity) {
//        return currentPage.get().isSame(activity);
//    }


    //导航目标有两种方式，一种是layout page切换，一种是activity切换。
    private Class targetClass;


    Navigation(Class idNavigationPage) {
        targetClass = idNavigationPage;
    }

    //发出导航事件
    public void fire() {
        EventBus.getDefault().post(this);
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
        if (targetClass.equals(activity.getClass())) {
            Log.i("NAVIGATION", "相同activity，不进行跳转");
            return;
        } else {
            Log.i("NAVIGATION", String.format("从%s跳转到%s", activity.getClass().getSimpleName(), targetClass.getSimpleName()));
        }
        if (targetClass != null) {
            if (intent == null)
                intent = new Intent(activity, targetClass);
            if (requestCodeForResult == null) {
                activity.startActivity(intent);
            } else {
                activity.startActivityForResult(intent, requestCodeForResult);
            }
        }
    }

    public void go(Activity activity, int requestCodeForResult) {
        go(activity, null, requestCodeForResult);
    }

    public void go(Activity activity) {

        go(activity, null, null);
    }

}
