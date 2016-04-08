package com.ivollo.familychat.chat.contact;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.contacts.entity.Contact;
import com.ivollo.chatcore.contacts.events.ContactListUpdatedEvent;
import com.ivollo.chatcore.contacts.events.InvitationListUpdatedEvent;
import com.ivollo.familychat.R;
import com.ivollo.familychat.commons.adapter.BindingRecyclerAdapter;
import com.ivollo.familychat.databinding.ChatItemInvitationBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:  好友列表的数据适配器
 * Author: 依宁
 * Created on 2016/3/18
 */
public class InvitationAdapter extends BindingRecyclerAdapter<Contact> {
    @Override
    public void bindData(ViewDataBinding binding, Contact contact) {
        ((ChatItemInvitationBinding) binding).setContact(contact);
    }

    @Override
    public int getItemLayoutRes(int viewType) {
        return R.layout.chat_item_invitation;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateList(InvitationListUpdatedEvent event) {
        setData(event.data);
    }
}
