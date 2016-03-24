package com.ivollo.familychat;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.ivollo.chatcore.bean.Friend;
import com.ivollo.chatcore.binding.ChatVM;
import com.ivollo.chatcore.binding.FriendVM;
import com.ivollo.chatcore.event.FriendInviteNavigateEvent;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.databinding.ActivityMainBinding;
import com.ivollo.familychat.friend.FriendListAdapter;
import com.ivollo.familychat.login.LoginActivity;
import com.ivollo.familychat.navigation.NavigateToLoginEvent;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends BindingActivity {
    /**
     * 被标记为@Inject的变量，会在执行component.inject(this)的时候自动被注入
     */
    @Inject
    MainVM mainVM;

    @Inject
    FriendVM friendVM;

    @Inject
    ChatVM chatVM;

    @Inject
    Navigator navigator;

    /**
     * 基类要求重载，返回ACTIVITY的布局XML id
     * 基类会调用数据绑定相关逻辑，将其设置为视图，并生成ViewDataBinding类
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Bind(R.id.recyclerView)
    carbon.widget.RecyclerView recyclerView;

    private FriendListAdapter friendListAdapter;
    private static final String TAG = "MainActivity";

    /**
     * 基类在使用getLayoutResId()返回的xml id进行数据绑定设置后，会将binding对象传入
     * 在这里需要将binding转换为本ACTIVITY实际生成的BINDING类(ActivityMainBinding)
     * 并将xml 的 data段里的所有variable依次进行赋值即可完成绑定。
     *
     * @param binding 数据绑定对象
     */
    @Override
    protected void initBinding(ViewDataBinding binding) {
        //dagger2 注入@inject 标注的成员
        TheApplication.getApplicationComponent().inject(this);

        //使用被注入的mainVM绑定到xml里data段的vm
        ((ActivityMainBinding) binding).setMainVM(mainVM);
        ((ActivityMainBinding) binding).setFriendVM(friendVM);
        ((ActivityMainBinding) binding).setNavigator(navigator);

        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        friendListAdapter = new FriendListAdapter();
        recyclerView.setAdapter(friendListAdapter);

        //调用vm层的好友数据加载方法
        //friendVM.refreshFriendList();

        //chatVM.registerChatMsgListener();
    }

    @Subscribe
    public void onFriendListLoaded(ArrayList<Friend> friends) {
        friendListAdapter.replaceList(friends);
    }

    @Subscribe
    public void navigateToLoginActivity(NavigateToLoginEvent event) {
        Log.i(TAG, "NAVIGATING TO LOGIN");
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Subscribe
    public void navigateToFriendInviateActivity(FriendInviteNavigateEvent event) {
        //startActivity(new Intent(this, FriendInviteActivity.class));
    }

    @Subscribe
    public void navigateToConversationActivity(Friend friend) {
        //ConversationActivity.launch(this, friend);
    }
}
