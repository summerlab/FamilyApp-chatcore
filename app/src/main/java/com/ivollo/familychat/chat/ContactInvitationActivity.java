package com.ivollo.familychat.chat;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.chatcore.event.FriendInviteSentEvent;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityContactInvitationBinding;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:  添加好友的UI视图页面
 * Author: 黄斐
 * Created on 2016/3/18
 */
public class ContactInvitationActivity extends BindingActivity {
    @Inject
    ChatVM chatVM;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_invitation;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        //dagger2 注入@inject 标注的成员
        TheApplication.getApplicationComponent().inject(this);
        //data binding
        ((ActivityContactInvitationBinding) binding).setVm(chatVM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Subscribe
    public void onFriendInvitationSent(FriendInviteSentEvent event) {
        //好友邀请发出后，从当前页面退回上一页
        finish();
    }
}
