package com.koohpar.oghli.ui.main;

import com.koohpar.oghli.api.RestManager;
import com.koohpar.oghli.data.DataManager;
import com.koohpar.oghli.ui.base.BaseViewModel;
import com.koohpar.oghli.utils.AppConstants;
import com.koohpar.oghli.utils.rx.SchedulersFacade;
import com.koohpar.oghli.utils.rx.SingleLiveData;

import io.reactivex.disposables.CompositeDisposable;

public class MainViewModel extends BaseViewModel<MainNavigator> implements AppConstants {

    public MainViewModel(DataManager dataManager, RestManager mRestManager, SchedulersFacade mSchedulersFacade, SingleLiveData<Integer> mToastLiveData, CompositeDisposable mCompositeDisposable) {
        super(dataManager, mRestManager, mSchedulersFacade, mToastLiveData, mCompositeDisposable);
    }

    public void openMenu() {
        getNavigator().openMenu();
    }

    public void openSum() {
        getNavigator().openSum();
    }

    public void openDistribute() {
        getNavigator().openDistribute();
    }

    public void openSearch() {
        getNavigator().openSearch();
    }

}
