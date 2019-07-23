package com.koohpar.oghli.utils.rx;

import io.reactivex.Scheduler;

/**
 * Created by behnaz on 10/29/2017.
 */

public interface SchedulerProvider  {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
