package com.ivollo.familychat;

import android.databinding.ViewDataBinding;
import android.support.annotation.MainThread;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ivollo.chatcore.ChatVM;
import com.ivollo.chatcore.contacts.events.ContactListRefreshApiResultEvent;
import com.ivollo.chatcore.contacts.events.ContactListUpdatedEvent;
import com.ivollo.chatcore.conversation.CreateConversationEvent;
import com.ivollo.chatcore.event.RefreshContactListEvent;
import com.ivollo.chatcore.event.ToastEvent;
import com.ivollo.familychat.chat.contact.ContactAdapter;
import com.ivollo.familychat.databinding.ActivityMainBinding;
import com.ivollo.familychat.commons.navigation.Navigation;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

public class MainActivity extends BaseActivity {
    /**
     * 被标记为@Inject的变量，会在执行component.inject(this)的时候自动被注入
     */
    @Inject
    MainVM mainVM;


    @Inject
    ChatVM chatVM;

    SwipeRefreshLayout swipeRefreshLayout;

    /**
     * 基类要求重载，返回ACTIVITY的布局XML id
     * 基类会调用数据绑定相关逻辑，将其设置为视图，并生成ViewDataBinding类
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Inject
    ContactAdapter contactAdapter;
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
        ((ActivityMainBinding) binding).setChatVM(chatVM);

        contactAdapter.setData(chatVM.getContactManager().contacts);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.contacts_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(contactAdapter);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_contacts);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new RefreshContactListEvent().fire();
            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onToastEvent(ToastEvent event) {
        Toast.makeText(this, event.message, Toast.LENGTH_SHORT).show();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onContactListUpdated(ContactListRefreshApiResultEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        if (event.isFailure) {
            Toast.makeText(this, event.errorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Subscribe
    public void onStartConversation(CreateConversationEvent event) {
        Navigation.CONVERSATION.fire();
    }

}
