package com.koohpar.oghli.ui.listPakhsh;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ListPakhshActivityModule {
    @Provides
    ListPakhshViewModel provideListPakhshViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        return new ListPakhshViewModel(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }
}
