package com.zone24x7.bi.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zone24x7.bi.logtracker.annotations.LogTracker;
import com.zone24x7.bi.logtracker.LogTrackerLogger;
import com.zone24x7.bi.sampleapp.controllers.TestController;
import com.zone24x7.bi.sampleapp.startup.LogTrackerHttpErrorHandler;

public class LoggerModule extends AbstractModule {


    @Override
    protected void configure() {
        //requestStaticInjection(TestController.class);
        //requestStaticInjection(LogTrackerHttpErrorHandler.class);
    }

    @Provides
    @LogTracker
    LogTrackerLogger provideLogger(LogTrackerLogger logTrackerLogger) {
        return logTrackerLogger;
    }
}
