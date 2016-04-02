package com.ivollo.familychat.commons.binding;

import android.databinding.BindingAdapter;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.EditText;
import com.ivollo.commons.R;
import com.ivollo.commons.binding.TwoWayBoundString;

/**
 * Copyright (2012-2016) by 杭州随行科技,Inc. All rights reserved
 * Comments:
 *
 * @author yining
 *         Created on 2016/4/2 11:19
 */
public class BindingAdapters {
    @BindingAdapter({"app:bindEditText"})
    public static void bindEditText(EditText view, final TwoWayBoundString twoWayBoundString) {
        Pair pair = (Pair) view.getTag(R.id.bind_edit_text);
        if (pair == null || pair.first != twoWayBoundString) {
            if (pair != null) {
                view.removeTextChangedListener((TextWatcher) pair.second);
            }
            TextWatcher watcher = new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    twoWayBoundString.set(s.toString());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            };
            view.setTag(R.id.bind_edit_text, new Pair<>(twoWayBoundString, watcher));
            view.addTextChangedListener(watcher);
        }
        String newValue = twoWayBoundString.get();
        if (!view.getText().toString().equals(newValue)) {
            view.setText(newValue);
        }
    }


    /**
     * 任意View使用app:onClick绑定事件，被绑定的函数无需遵循
     * functionName(View v) 的格式
     * functionName() 即可
     */
    @BindingAdapter({"app:onClick"})
    public static void bindOnClick(View view, final Runnable runnable) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runnable.run();
            }
        });
    }
}
