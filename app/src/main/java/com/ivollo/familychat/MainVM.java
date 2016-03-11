package com.ivollo.familychat;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.ivollo.commons.api.ApiCallback;
import com.ivollo.commons.binding.TwoWayBoundString;
import com.ivollo.timescore.TimesApi;
import com.ivollo.timescore.TimesLineResponse;

/**
 * Comments:ViewModel
 * 所有业务逻辑的处理层。此层与视图无关，不应该包含任何Activity/Fragment等视图相关的类型和控件。
 *
 * @author yining
 *         Created on 2016/3/9 16:28
 */
public class MainVM {
    TimesApi timesApi;

    //被绑定到第二个按钮的文本
    public TwoWayBoundString statusText = new TwoWayBoundString();
    //被绑定到第二个按钮的Enabled属性
    public ObservableBoolean btn2Enabled = new ObservableBoolean();
    //被绑定到第一个按钮的文本
    public ObservableInt count = new ObservableInt(0);

    public MainVM(TimesApi api) {
        this.timesApi = api;
        statusText.set("获取");
        btn2Enabled.set(true);
    }


    public void updateCount(View v) {
        count.set(count.get() + 1);
    }


    public void getList(View v) {
        btn2Enabled.set(false);
        statusText.set("获取中...");


        timesApi.updateTimes("15", System.currentTimeMillis()).enqueue(new ApiCallback<TimesLineResponse>() {
            @Override
            public void onApiSuccess(TimesLineResponse apiResponse) {
                Log.i(this.getClass().getSimpleName(), "NEVER SUCCESS");
                resetButton();
            }

            @Override
            public void onApiError(TimesLineResponse apiResponse) {
                Log.i(this.getClass().getSimpleName(), "NEVER API ERROR");
                resetButton();
            }

            @Override
            public void onOtherErrors(String reason) {
                Log.i(this.getClass().getSimpleName(), "OTHER ERRORS");
                resetButton();
            }

            private void resetButton(){
                //在API访问结束后，恢复按钮状态，并设置按钮文本
                statusText.set("失败");
                btn2Enabled.set(true);
            }
        });
    }
}
