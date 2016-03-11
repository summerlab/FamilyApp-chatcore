package com.ivollo.familychat.di;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.easemob.chat.EMChat;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ivollo.commons.api.oauth.OAuth;
import com.ivollo.commons.api.oauth.OAuthApi;
import com.ivollo.commons.di.PerActivity;
import com.ivollo.commons.di.PerApplication;
import com.ivollo.familychat.MainVM;
import com.ivollo.timescore.TimesApi;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.util.List;
import java.util.Map;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

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
    MainVM mainVM(TimesApi timesApi) {
        return new MainVM(timesApi);
    }
}
