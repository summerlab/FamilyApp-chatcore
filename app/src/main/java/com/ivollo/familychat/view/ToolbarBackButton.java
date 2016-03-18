package com.ivollo.familychat.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ivollo.familychat.R;

import net.steamcrafted.materialiconlib.MaterialIconView;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/18 9:19
 */
public class ToolbarBackButton extends MaterialIconView {
    public ToolbarBackButton(Context context) {
        this(context, null);
    }

    public ToolbarBackButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(getContext(), R.layout.toolbar_back_button, (ViewGroup) getParent());
        setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) getContext()).onBackPressed();
            }
        });
    }
}
