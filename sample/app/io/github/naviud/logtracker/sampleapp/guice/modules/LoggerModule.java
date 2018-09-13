package io.github.naviud.logtracker.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.github.naviud.logtracker.annotations.LogTracker;
import io.github.naviud.logtracker.LogTrackerLogger;

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
