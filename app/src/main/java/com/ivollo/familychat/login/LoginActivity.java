package com.ivollo.familychat.login;

import android.databinding.ViewDataBinding;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import com.ivollo.commons.base.BindingActivity;
import com.ivollo.commons.event.UserLoginResultEvent;
import com.ivollo.familychat.R;
import com.ivollo.familychat.TheApplication;
import com.ivollo.familychat.databinding.ActivityLoginBinding;

import net.steamcrafted.materialiconlib.MaterialDrawableBuilder;
import net.steamcrafted.materialiconlib.MaterialIconView;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/18 10:03
 */
public class LoginActivity extends BindingActivity {
    @Inject
    LoginVM loginVM;


    @Bind(R.id.check_password_visible)
    MaterialIconView iconPasswordVisible;


    @Override
    protected int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initBinding(ViewDataBinding binding) {
        //dagger2 注入@inject 标注的成员
        TheApplication.getApplicationComponent().inject(this);
        ((ActivityLoginBinding) binding).setLoginVM(loginVM);
        ButterKnife.bind(this);
    }

    @Subscribe
    public void onLoginResult(UserLoginResultEvent event) {
        if (event.isSuccess)
            finish();
        else
            Toast.makeText(this, event.errorMessage, Toast.LENGTH_SHORT).show();
    }

    //密码输入框显示明文和关闭明文相关逻辑
    @Bind(R.id.password)
    EditText editPassword;
    private boolean isPasswordVisible = false;

    @OnClick(R.id.check_password_visible)
    public void onPasswordVisibilityChange() {
        isPasswordVisible = !isPasswordVisible;
        if (isPasswordVisible) {
            iconPasswordVisible.setIcon(MaterialDrawableBuilder.IconValue.EYE);
            iconPasswordVisible.setColorResource(R.color.check_icon_checked);
            editPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            iconPasswordVisible.setIcon(MaterialDrawableBuilder.IconValue.EYE_OFF);
            iconPasswordVisible.setColorResource(R.color.check_icon_unchecked);
            editPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        editPassword.setSelection(editPassword.getText().length());
    }
    //-------------------------------------

}
