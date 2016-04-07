package com.ivollo.familychat.commons.adapter;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ivollo.commons.base.DataSetUpdateEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:用于RecyclerView的数据适配器基类
 * <p>
 * DATA是子元素的类型
 * EVENT是用于更新该适配器的EVENT类型
 * 适配器会自动捕获EVENT类型的事件并将数据更新为EVENT.data
 *
 * @author yining
 *         Created on 2016/3/31 13:18
 */
public abstract class BindingRecyclerAdapter<DATA> extends RecyclerView.Adapter<BindingViewHolder> {

    private List<DATA> mList = new ArrayList<>();

    public BindingRecyclerAdapter() {
        try {
            EventBus.getDefault().register(this);
        }catch (Exception expected){
            //没有通过事件来通知数据更新
        }
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), getItemLayoutRes(viewType), parent, false);
        BindingViewHolder holder = new BindingViewHolder(binding);
        binding.executePendingBindings();
        return holder;
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {
        ViewDataBinding binding = holder.getViewDataBinding();
        bindData(binding, getItem(position));
        binding.executePendingBindings();
    }

    public abstract void bindData(ViewDataBinding binding, DATA data);

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public DATA getItem(int position) {
        return mList.get(position);
    }

    public void appendData(List<DATA> data) {
        if (data == null || data.isEmpty())
            return;
        mList.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<DATA> data) {

        if (data == null || data.isEmpty()) {
            mList.clear();
        } else {
            mList = data;
        }
        notifyDataSetChanged();
    }

    //派生类重载此函数来返回viewType对应的item_layout的资源id
    @LayoutRes
    public abstract int getItemLayoutRes(int viewType);

}
