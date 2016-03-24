package com.ivollo.familychat;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableLong;
import android.view.View;

import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.commons.event.OAuth2TokenFailureEvent;
import com.ivollo.commons.event.OAuth2TokenUpdatedEvent;
import com.ivollo.commons.utils.MD5;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Comments:ViewModel
 * 所有业务逻辑的处理层。此层与视图无关，不应该包含任何Activity/Fragment等视图相关的类型和控件。
 *
 * @author yining
 *         Created on 2016/3/9 16:28
 */
public class MainVM {


    //被绑定到第二个按钮的文本
    public ObservableField<String> accessToken = new ObservableField<>("--");
    public ObservableField<String> refreshToken = new ObservableField<>("--");
    public ObservableLong expireTime = new ObservableLong(0);
    //被绑定到第二个按钮的Enabled属性
    public ObservableBoolean btn2Enabled = new ObservableBoolean();
    //被绑定到第一个按钮的文本
    public ObservableInt count = new ObservableInt(0);

    private OAuth2 oAuth2;

    public MainVM(OAuth2 oAuth2) {
        this.oAuth2 = oAuth2;
        updateFromOAuth2();
        btn2Enabled.set(true);
        EventBus.getDefault().register(this);
    }

    public void getToken(View v) {
        accessToken.set(oAuth2.getAccessToken());
    }

    public void login(View v) {
        oAuth2.login("13588777739", MD5.encodePassword("a12345"));
    }

    public void register(View v) {
        oAuth2.register("13588777740", MD5.encodePassword("a12345"), false, "测试", null);
    }

    public void logout(View v) {
        oAuth2.logout();
    }

    @Subscribe
    public void onOAuthSuccess(OAuth2TokenUpdatedEvent event) {
        accessToken.set(event.accessToken);
        refreshToken.set(event.refreshToken);
        expireTime.set(event.expireTime);
    }

    @Subscribe
    public void onOAuthFailure(OAuth2TokenFailureEvent event) {
        updateFromOAuth2();
    }

    private void updateFromOAuth2(){
        accessToken.set(oAuth2.accessToken);
        refreshToken.set(oAuth2.refreshToken);
        expireTime.set(oAuth2.expireTime);
    }
}
