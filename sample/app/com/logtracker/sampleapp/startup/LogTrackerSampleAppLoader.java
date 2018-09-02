package com.logtracker.sampleapp.startup;

import com.logtracker.LogTrackerModule;
import com.logtracker.sampleapp.guice.modules.ControllerModule;
import com.logtracker.sampleapp.guice.modules.LoggerModule;
import play.ApplicationLoader;
import play.inject.guice.GuiceApplicationBuilder;
import play.inject.guice.GuiceApplicationLoader;

public class LogTrackerSampleAppLoader extends GuiceApplicationLoader {

    @Override
    public GuiceApplicationBuilder builder(ApplicationLoader.Context context) {
        return initialBuilder
                .in(context.environment())
                .bindings(new LogTrackerModule())
                .bindings(new LoggerModule())
                .bindings(new ControllerModule());
    }
}
