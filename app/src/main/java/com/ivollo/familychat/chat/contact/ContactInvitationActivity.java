package com.ivollo.familychat.chat.contact;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.chatcore.contacts.InvitationManager;
import com.ivollo.familychat.BaseActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityContactInvitationBinding;

import javax.inject.Inject;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:  好友邀请Activity
 * Author: yining
 * Created on 2016/3/18
 */
public class ContactInvitationActivity extends BaseActivity {
    @Inject
    InvitationManager invitationManager;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_invitation;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        TheApplication.getApplicationComponent().inject(this);
        ((ActivityContactInvitationBinding) binding).setInvitationManager(invitationManager);
    }
}
