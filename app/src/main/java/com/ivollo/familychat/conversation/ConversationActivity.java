package com.ivollo.familychat.conversation;

import android.content.Context;
import android.content.Intent;
import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.bean.Friend;
import com.ivollo.commons.base.BindingActivity;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments: 聊天对话的页面（单聊）
 * Author: 黄斐
 * Created on 2016/3/22
 */
public class ConversationActivity extends BindingActivity {
    public static void launch(Context context, Friend friend) {
        context.startActivity(new Intent(context, ConversationActivity.class).putExtra(EXTRA_FRIEND, friend));
    }

    private static final String EXTRA_FRIEND = "friend";
    private Friend friend;

    @Override
    protected int getLayoutResId() {
        return 0;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        friend = (Friend) getIntent().getSerializableExtra(EXTRA_FRIEND);
    }
}
