package com.koohpar.oghli.di.module;


import android.support.annotation.StringRes;

import io.reactivex.functions.Consumer;


public abstract class RxRetrofitErrorConsumer implements Consumer<Throwable> {
    @Override
    public void accept(Throwable e) {
        int errorStringID = RetrofitErrorHandler.getError(e);
        handleError(e, errorStringID);
    }

    public abstract void handleError(Throwable throwable, @StringRes int id);
}
