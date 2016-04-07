package com.ivollo.familychat.chat.conversation;

import android.databinding.ViewDataBinding;

import com.hyphenate.chat.EMMessage;
import com.ivollo.chatcore.api.MessagesUpdatedEventSet;
import com.ivollo.familychat.R;
import com.ivollo.familychat.commons.adapter.EventBasedRecyclerAdapter;
import com.ivollo.familychat.databinding.ChatItemMessageTextBinding;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/30 19:08
 */
public class MessageAdapter extends EventBasedRecyclerAdapter<EMMessage, MessagesUpdatedEventSet> {

    @Override
    public int getItemLayoutRes(int viewType) {
        return R.layout.chat_item_message_text;
    }

    @Override
    public void bindData(ViewDataBinding binding, EMMessage emMessage) {
        ((ChatItemMessageTextBinding) binding).setMessage(emMessage);
    }
}
