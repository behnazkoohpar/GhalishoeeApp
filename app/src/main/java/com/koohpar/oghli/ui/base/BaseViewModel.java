/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.koohpar.oghli.ui.base;


import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.ui.login.LoginActivity;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by behnaz on 07/07/17.
 */

public abstract class BaseViewModel<N> extends ViewModel {

    protected final DataManager mDataManager;
    protected final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    protected final RestManager mRestManager;
    protected final SchedulersFacade mSchedulersFacade;
    protected final SingleLiveData<Integer> mToastLiveData;
    protected final CompositeDisposable mCompositeDisposable;
    private N mNavigator;

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public BaseViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        this.mRestManager = mRestManager;
        this.mDataManager = dataManager;
        this.mSchedulersFacade = mSchedulersFacade;
        this.mToastLiveData = mToastLiveData;
        this.mCompositeDisposable = mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public RestManager getmRestManager() {
        return mRestManager;
    }

    public SchedulersFacade getmSchedulersFacade() {
        return mSchedulersFacade;
    }

    public SingleLiveData<Integer> getmToastLiveData() {
        return mToastLiveData;
    }

}
