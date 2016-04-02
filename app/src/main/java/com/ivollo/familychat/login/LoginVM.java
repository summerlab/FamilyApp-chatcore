package com.ivollo.familychat.login;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.text.TextUtils;
import android.view.View;

import com.ivollo.commons.api.AccountApi;
import com.ivollo.commons.api.NODATA;
import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.commons.base.ApiCallback;
import com.ivollo.commons.base.ApiResponse;
import com.ivollo.commons.binding.TwoWayBoundString;
import com.ivollo.commons.event.OAuth2TokenFailureEvent;
import com.ivollo.commons.event.UserLoginResultEvent;
import com.ivollo.commons.utils.MD5;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/18 10:03
 */
public class LoginVM {
    public ObservableBoolean loginInProgress = new ObservableBoolean(false);
    public ObservableField<String> loginBtnText = new ObservableField<>("登 录");
    public ObservableField<String> registerBtnText = new ObservableField<>("注 册");
    public TwoWayBoundString username = new TwoWayBoundString();
    public TwoWayBoundString password = new TwoWayBoundString();

    private static final String TAG = "LoginVM";
    private AccountApi accountApi;
    private OAuth2 oAuth2;

    public LoginVM(OAuth2 oAuth2, AccountApi accountApi) {
        this.oAuth2 = oAuth2;
        this.accountApi = accountApi;
        EventBus.getDefault().register(this);
    }

    /**
     * 点击按钮触发用户登录事件
     */
    public void login(View v) {
        doLogin();
    }

    private void doLogin() {
        if (TextUtils.isEmpty(username.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入手机号码"));
            return;
        } else if (TextUtils.isEmpty(password.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入登陆密码"));
            return;
        }

        //禁用登录按钮
        loginInProcess(true);
        oAuth2.login(username.get(), password.get());
    }

    /**
     * 点击按钮触发用户注册事件
     */
    public void register(View v) {
        if (TextUtils.isEmpty(username.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入手机号码"));
            return;
        } else if (TextUtils.isEmpty(password.get())) {
            EventBus.getDefault().post(new OAuth2TokenFailureEvent("请输入登陆密码"));
            return;
        }

        registerInProcess(true);
        accountApi.register(username.get(), MD5.encodePassword(password.get()), 1, "", "").enqueue(new ApiCallback<NODATA>() {
            @Override
            public void onResponse(Call<ApiResponse<NODATA>> call, Response<ApiResponse<NODATA>> response) {
                super.onResponse(call, response);
                registerInProcess(false);
            }

            @Override
            public void onApiSuccess(NODATA nodata) {
                //注册成功后直接调用登录逻辑
                doLogin();
            }

            @Override
            public void onApiFailure(int errorCode, String errorMessage) {
                EventBus.getDefault().post(new OAuth2TokenFailureEvent(errorMessage));
            }
        });
    }


    @Subscribe
    public void onLoginResult(UserLoginResultEvent event) {
        loginInProcess(false);
    }

    private void loginInProcess(boolean inProcess) {
        loginBtnText.set(inProcess ? "登录中..." : "登 录");
        loginInProgress.set(inProcess);
    }

    private void registerInProcess(boolean inProcess) {
        registerBtnText.set(inProcess ? "注册中..." : "注 册");
        loginInProgress.set(inProcess);
    }
}
