package com.ivollo.familychat.friend;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.binding.FriendVM;
import com.ivollo.chatcore.event.AcitvityFinishEvent;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityFriendInviteBinding;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:  添加好友的UI视图页面
 * Author: 黄斐
 * Created on 2016/3/18
 */
public class FriendInviteActivity extends BindingActivity {
    @Inject
    FriendVM friendVM;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_friend_invite;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        //dagger2 注入@inject 标注的成员
        TheApplication.getApplicationComponent().inject(this);
        //data binding
        ((ActivityFriendInviteBinding) binding).setVm(friendVM);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Subscribe
    public void onFriendInvitationSended(AcitvityFinishEvent event) {
        //好友邀请发出后，从当前页面退回上一页
        finish();
    }
}
