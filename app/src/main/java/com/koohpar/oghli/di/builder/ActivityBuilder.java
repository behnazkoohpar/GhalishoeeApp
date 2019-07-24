package com.koohpar.oghli.di.builder;

import com.koohpar.oghli.ui.listCustomer.ListCustomerActivity;
import com.koohpar.oghli.ui.listCustomer.ListCustomerActivityModule;
import com.koohpar.oghli.ui.listPakhsh.ListPakhshActivity;
import com.koohpar.oghli.ui.listPakhsh.ListPakhshActivityModule;
import com.koohpar.oghli.ui.listSum.ListSumActivity;
import com.koohpar.oghli.ui.listSum.ListSumActivityModule;
import com.koohpar.oghli.ui.login.LoginActivity;
import com.koohpar.oghli.ui.login.LoginActivityModule;
import com.koohpar.oghli.ui.main.MainActivity;
import com.koohpar.oghli.ui.main.MainActivityModule;
import com.koohpar.oghli.ui.order.OrderActivity;
import com.koohpar.oghli.ui.order.OrderActivityModule;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerActivity;
import com.koohpar.oghli.ui.searchCustomer.SearchCustomerActivityModule;
import com.koohpar.oghli.ui.showOrder.ShowOrderActivity;
import com.koohpar.oghli.ui.showOrder.ShowOrderActivityModule;
import com.koohpar.oghli.ui.signUpCustomer.SignUpCustomerActivity;
import com.koohpar.oghli.ui.signUpCustomer.SignUpCustomerActivityModule;
import com.koohpar.oghli.ui.splash.SplashActivity;
import com.koohpar.oghli.ui.splash.SplashActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = SignUpCustomerActivityModule.class)
    abstract SignUpCustomerActivity bindSignUpCustomerActivity();

    @ContributesAndroidInjector(modules = SearchCustomerActivityModule.class)
    abstract SearchCustomerActivity bindSearchCustomerActivity();

    @ContributesAndroidInjector(modules = OrderActivityModule.class)
    abstract OrderActivity bindOrderActivity();

    @ContributesAndroidInjector(modules = ShowOrderActivityModule.class)
    abstract ShowOrderActivity bindShowOrderActivity();

    @ContributesAndroidInjector(modules = ListCustomerActivityModule.class)
    abstract ListCustomerActivity bindListCustomerActivity();

    @ContributesAndroidInjector(modules = ListSumActivityModule.class)
    abstract ListSumActivity bindListSumActivity();

    @ContributesAndroidInjector(modules = ListPakhshActivityModule.class)
    abstract ListPakhshActivity bindListPakhshActivity();

}

