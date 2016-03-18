package com.ivollo.familychat.friend;

import com.ivollo.chatcore.bean.Friend;
import com.ivollo.familychat.R;
import com.ivollo.familychat.adapter.BindingAdapter;
import com.ivollo.familychat.adapter.BindingHolder;
import com.ivollo.familychat.databinding.ItemFriendBinding;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:  好友列表的数据适配器
 * Author: 黄斐
 * Created on 2016/3/18
 */
public class FriendListAdapter extends BindingAdapter<Friend> {
    @Override
    public void onBindViewHolder(BindingHolder holder, int position) {
        ItemFriendBinding binding = (ItemFriendBinding) holder.getBinding();
        binding.setBean(getItem(position));
        binding.executePendingBindings();
    }

    @Override
    public int getConvertView(int viewType) {
        return R.layout.item_friend;
    }
}
