package com.ivollo.familychat.chat.conversation;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.chatcore.conversation.ConversationManager;
import com.ivollo.chatcore.conversation.MessageListUpdatedEvent;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityConversationBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/31 14:15
 */
public class ConversationActivity extends BindingActivity {
    @Inject
    ConversationManager conversationManager;


    @Inject
    MessageAdapter messageAdapter;

    @Bind(R.id.recycler_conversation)
    RecyclerView invitationRecycler;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_conversation;
    }


    @Override
    protected void initBinding(ViewDataBinding binding) {
        TheApplication.getApplicationComponent().inject(this);
        ((ActivityConversationBinding) binding).setConversationManager(conversationManager);
        ButterKnife.bind(this);
        //初始化已保存的邀请信息
        messageAdapter.setData(conversationManager.messages);
        //设置RecyclerView
        invitationRecycler.setLayoutManager(new LinearLayoutManager(this));
        invitationRecycler.setAdapter(messageAdapter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageListUpdatedEvent(MessageListUpdatedEvent event) {
        //滚动到最后。
        invitationRecycler.scrollToPosition(event.data.size() - 1);
    }

}
