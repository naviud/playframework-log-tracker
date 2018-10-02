package io.github.naviud.logtracker.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import io.github.naviud.logtracker.annotations.LogTracker;
import io.github.naviud.logtracker.LogTrackerLogger;
import io.github.naviud.logtracker.sampleapp.tasks.TestCallableTask;
import io.github.naviud.logtracker.sampleapp.tasks.TestTask;

public class LoggerModule extends AbstractModule {


    @Override
    protected void configure() {
        //requestStaticInjection(TestController.class);
        //requestStaticInjection(LogTrackerHttpErrorHandler.class);
        requestStaticInjection(TestCallableTask.class);
        requestStaticInjection(TestTask.class);
    }

    @Provides
    @LogTracker
    LogTrackerLogger provideLogger(LogTrackerLogger logTrackerLogger) {
        return logTrackerLogger;
    }
}
