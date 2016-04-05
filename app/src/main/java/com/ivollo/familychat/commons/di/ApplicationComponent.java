package com.ivollo.familychat.commons.di;

import com.ivollo.chatcore.di.ChatModule;
import com.ivollo.commons.api.oauth.OAuth2;
import com.ivollo.commons.di.CommonModule;
import com.ivollo.familychat.MainActivity;
import com.ivollo.familychat.chat.contact.ContactInvitationActivity;
import com.ivollo.familychat.chat.contact.ContactOperationActivity;
import com.ivollo.familychat.chat.conversation.ConversationActivity;
import com.ivollo.familychat.login.LoginActivity;
import com.ivollo.timescore.di.TimesModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Comments:
 *
 * @author yining
 *         Created on 2016/3/9 12:57
 */
@Singleton
@Component(modules = {CommonModule.class, ChatModule.class, TimesModule.class, ApplicationModule.class})
public interface ApplicationComponent {
    void inject(MainActivity activity);

    void inject(LoginActivity activity);

    void inject(ContactOperationActivity activity);

    void inject(ContactInvitationActivity activity);

    void inject(ConversationActivity activity);
}

