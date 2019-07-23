package com.koohpar.oghli.di.module;

import android.app.Application;
import android.content.Context;

import com.koohpar.oghli.api.AppRestManager;
import com.koohpar.oghli.api.ICallApi;
import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.AppDataManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.data.pref.AppPreferencesHelper;
import com.koohpar.oghli.data.pref.PreferencesHelper;
import com.koohpar.oghli.di.PreferenceInfo;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Retrofit;

/**
 * Created by behnaz on 10/29/2017.
 */

@Module
public class AppModule implements AppConstants {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    ICallApi provideICallApi(Retrofit retrofit) {
        return retrofit.create(ICallApi.class);
    }

    @Singleton
    @Provides
    static RestManager provideRestManager(ICallApi iCallApi ) {
     return new AppRestManager(iCallApi);
    }

    @Provides
    static CompositeDisposable provideCompositeDisposable(){
        return new CompositeDisposable();
    }

    @Provides
    static SingleLiveData<Integer> provideIntegerSingleLiveData(){
        return new SingleLiveData<>();
    }


}
