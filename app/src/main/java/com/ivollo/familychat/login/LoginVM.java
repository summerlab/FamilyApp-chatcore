package com.ivollo.familychat.login;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.view.View;

import com.ivollo.commons.account.AccountApi;
import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.commons.api.oauth.OAuth2TokenFailureEvent;
import com.ivollo.commons.api.oauth.OAuth2TokenUpdatedEvent;
import com.ivollo.commons.binding.TwoWayBoundBoolean;
import com.ivollo.commons.binding.TwoWayBoundString;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/18 10:03
 */
public class LoginVM {
    public ObservableBoolean logoutBtnVisibility = new ObservableBoolean(false);
    public ObservableBoolean loginBtnEnabled = new ObservableBoolean(true);
    public ObservableField<String> loginBtnText = new ObservableField<>("登录");
    public TwoWayBoundString username = new TwoWayBoundString();
    public TwoWayBoundString password = new TwoWayBoundString();

    private AccountApi accountApi;
    private OAuth2 oAuth2;

    public LoginVM(OAuth2 oAuth2, AccountApi accountApi) {
        this.oAuth2 = oAuth2;
        this.accountApi = accountApi;
        EventBus.getDefault().register(this);
    }

    public void login(View v) {

        //禁用登录按钮
        loginInProcess(true);
        oAuth2.login(username.get(), password.get());
    }

    @Subscribe
    public void onLoginSuccess(OAuth2TokenUpdatedEvent event) {

        loginInProcess(false);
    }

    @Subscribe
    public void onLoginFailure(OAuth2TokenFailureEvent event) {
        loginInProcess(false);
    }

    private void loginInProcess(boolean inProcess) {
        loginBtnText.set(inProcess ? "登录中..." : "登录");
        loginBtnEnabled.set(!inProcess);
    }
}
