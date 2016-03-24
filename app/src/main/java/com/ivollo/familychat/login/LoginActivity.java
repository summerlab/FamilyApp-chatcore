package com.ivollo.familychat.login;

import android.databinding.ViewDataBinding;
import android.widget.Toast;

import com.ivollo.commons.base.BindingActivity;
import com.ivollo.commons.event.UserLoginResultEvent;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityLoginBinding;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/18 10:03
 */
public class LoginActivity extends BindingActivity {
    @Inject
    LoginVM loginVM;

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        //dagger2 注入@inject 标注的成员
        TheApplication.getApplicationComponent().inject(this);

        ((ActivityLoginBinding) binding).setLoginVM(loginVM);
    }

    @Subscribe
    public void onLoginResult(UserLoginResultEvent event) {
        if (event.isSuccess)
            finish();
        else
            Toast.makeText(this, event.errorMessage, Toast.LENGTH_SHORT).show();
    }
}
