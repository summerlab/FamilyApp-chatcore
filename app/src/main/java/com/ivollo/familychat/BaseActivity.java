package com.ivollo.familychat;

import com.ivollo.commons.api.oauth.OAuthLoginRequiredEvent;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.commons.navigation.Navigation;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:
 *
 * @author yining
 *         Created on 2016/4/2 13:54
 */
public abstract class BaseActivity extends BindingActivity {
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginRequired(OAuthLoginRequiredEvent event) {
        Navigation.LOGIN.fire();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNavigation(Navigation navigation) {
        navigation.go(this);
    }
}
