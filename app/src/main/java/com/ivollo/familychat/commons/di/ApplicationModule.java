package com.ivollo.familychat.commons.di;

import android.app.Application;

import com.ivollo.commons.api.AccountApi;
import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.familychat.MainVM;
import com.ivollo.familychat.chat.contact.ContactAdapter;
import com.ivollo.familychat.chat.conversation.MessageAdapter;
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
    }

    @Provides
    @Singleton
    LoginVM loginVM(OAuth2 oAuth2, AccountApi accountApi) {
        return new LoginVM(oAuth2, accountApi);
    }

    @Provides
    @Singleton
    MainVM mainVM(OAuth2 oAuth2) {
        return new MainVM(oAuth2);
    }

    @Provides
    @Singleton
    ContactAdapter contactAdapter() {
        return new ContactAdapter();
    }

    @Provides
    @Singleton
    MessageAdapter messageAdapter() {
        return new MessageAdapter();
    }
}
