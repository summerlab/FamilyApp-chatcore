package com.ivollo.familychat.chat.contact;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.BaseActivity;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.commons.navigation.Navigation;
import com.ivollo.familychat.databinding.ActivityContactOperationBinding;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:联系人操作界面
 *
 * @author yining
 *         Created on 2016/4/1 14:04
 */
public class ContactOperationActivity extends BaseActivity {
    @Inject
    ChatVM chatVM;
    @Inject
    InvitationAdapter invitationAdapter;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_contact_operation;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        TheApplication.getApplicationComponent().inject(this);
        ((ActivityContactOperationBinding) binding).setVm(chatVM);

        //初始化已保存的邀请信息
        invitationAdapter.setData(chatVM.getInvitationManager().getInvitations());

        //设置RecyclerView
        RecyclerView invitationRecycler = (RecyclerView) findViewById(R.id.invitation_recycler);
        invitationRecycler.setLayoutManager(new LinearLayoutManager(this));
        invitationRecycler.setAdapter(invitationAdapter);

    }


}
