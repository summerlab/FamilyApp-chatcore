package com.ivollo.familychat;

import android.databinding.ViewDataBinding;

import com.ivollo.chatcore.binding.ChatVM;
import com.ivollo.commons.base.BindingActivity;
import com.ivollo.familychat.databinding.ActivityMainBinding;

import javax.inject.Inject;

public class MainActivity extends BindingActivity {

    /**
     * 基类要求重载，返回ACTIVITY的布局XML id
     * 基类会调用数据绑定相关逻辑，将其设置为视图，并生成ViewDataBinding类
     */
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    /**
     * 被标记为@Inject的变量，会在执行component.inject(this)的时候自动被注入
     */
    @Inject
    MainVM mainVM;

    @Inject
    ChatVM chatVM;

    @Inject
    Navigator navigator;

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
        ((ActivityMainBinding) binding).setNavigator(navigator);

    }


}
