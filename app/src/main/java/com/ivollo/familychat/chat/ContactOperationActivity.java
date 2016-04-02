package com.ivollo.familychat.chat;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityContactOperationBinding;

import javax.inject.Inject;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:联系人操作界面
 *
 * @author yining
 *         Created on 2016/4/1 14:04
 */
public class ContactOperationActivity extends BindingActivity {
    @Inject
    ChatVM chatVM;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_operation;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        TheApplication.getApplicationComponent().inject(this);
        ((ActivityContactOperationBinding) binding).setVm(chatVM);
    }
}
