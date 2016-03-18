package com.ivollo.familychat.di;

import android.app.Application;

import com.easemob.chat.EMChat;
import com.ivollo.commons.account.AccountApi;
import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.familychat.MainVM;
import com.ivollo.familychat.Navigator;
import com.ivollo.familychat.login.LoginVM;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/9 11:33
 */
@Module
public class ApplicationModule {

    Application application;

    public ApplicationModule(Application application) {
        this.application = application;
        EMChat.getInstance().init(application);
    }

    @Provides
    @Singleton
    EMChat providesEMChat() {
        return EMChat.getInstance();
    }

    @Provides
    @Singleton
    MainVM mainVM(OAuth2 oAuth2) {
        return new MainVM(oAuth2);
    }

    @Provides
    @Singleton
    Navigator navigator() {
        return new Navigator();
    }

    @Provides
    @Singleton
    LoginVM loginVM(OAuth2 oAuth2, AccountApi accountApi) {
        return new LoginVM(oAuth2, accountApi);
    }
}
