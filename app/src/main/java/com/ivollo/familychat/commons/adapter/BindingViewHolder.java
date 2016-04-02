package com.ivollo.familychat.commons.adapter;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:用于数据绑定的RecyclerView.ViewHolder
 *
 * @author yining
 *         Created on 2016/3/30 20:04
 */
public class BindingViewHolder extends RecyclerView.ViewHolder {
    private ViewDataBinding mViewDataBinding;

    public BindingViewHolder(ViewDataBinding viewDataBinding) {
        super(viewDataBinding.getRoot());

        mViewDataBinding = viewDataBinding;
        mViewDataBinding.executePendingBindings();
    }

    public ViewDataBinding getViewDataBinding() {
        return mViewDataBinding;
    }
}
