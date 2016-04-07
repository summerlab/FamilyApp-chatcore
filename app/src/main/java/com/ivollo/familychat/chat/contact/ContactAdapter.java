package com.ivollo.familychat.chat.contact;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.api.MessagesListUpdatedEvent;
import com.ivollo.chatcore.contacts.entity.Contact;
import com.ivollo.chatcore.contacts.events.ContactListUpdatedEvent;
import com.ivollo.familychat.R;
import com.ivollo.familychat.commons.adapter.BindingRecyclerAdapter;
import com.ivollo.familychat.databinding.ChatItemContactBinding;

import org.greenrobot.eventbus.Subscribe;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:  好友列表的数据适配器
 * Author: 依宁
 * Created on 2016/3/18
 */
public class ContactAdapter extends BindingRecyclerAdapter<Contact> {
    @Override
    public void bindData(ViewDataBinding binding, Contact contact) {
        ((ChatItemContactBinding) binding).setContact(contact);
    }

    @Override
    public int getItemLayoutRes(int viewType) {
        return R.layout.chat_item_contact;
    }

    @Subscribe
    public void onUpdateList(ContactListUpdatedEvent event) {
        setData(event.data);
    }
}
