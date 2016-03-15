package com.ivollo.familychat;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.ivollo.commons.api.ApiCallback;
import com.ivollo.commons.binding.TwoWayBoundString;

/**
 * Comments:ViewModel
 * 所有业务逻辑的处理层。此层与视图无关，不应该包含任何Activity/Fragment等视图相关的类型和控件。
 *
 * @author yining
 *         Created on 2016/3/9 16:28
 */
public class MainVM {


    //被绑定到第二个按钮的文本
    public ObservableField<String> statusText = new ObservableField<>("0");
    //被绑定到第二个按钮的Enabled属性
    public ObservableBoolean btn2Enabled = new ObservableBoolean();
    //被绑定到第一个按钮的文本
    public ObservableInt count = new ObservableInt(0);

    public MainVM() {
        statusText.set("获取");
        btn2Enabled.set(true);
    }


    public void updateCount(View v) {
        count.set(count.get() + 1);
    }


    public void getList(View v) {
        btn2Enabled.set(false);
        statusText.set("获取中...");
    }
}
