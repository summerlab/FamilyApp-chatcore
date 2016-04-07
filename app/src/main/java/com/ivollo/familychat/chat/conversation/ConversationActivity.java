package com.ivollo.familychat.chat.conversation;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityConversationBinding;

import javax.inject.Inject;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/31 14:15
 */
public class ConversationActivity extends BindingActivity {
    @Inject
    ChatVM chatVM;


    @Inject
    MessageAdapter messageAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_conversation;
    }


    @Override
    protected void initBinding(ViewDataBinding binding) {
        TheApplication.getApplicationComponent().inject(this);
        ((ActivityConversationBinding) binding).setVm(chatVM);

        //初始化已保存的邀请信息
        messageAdapter.setData(chatVM.conversationManager.messages);

        //设置RecyclerView
        RecyclerView invitationRecycler = (RecyclerView) findViewById(R.id.recycler_conversation);
        invitationRecycler.setLayoutManager(new LinearLayoutManager(this));
        invitationRecycler.setAdapter(messageAdapter);
    }
}
