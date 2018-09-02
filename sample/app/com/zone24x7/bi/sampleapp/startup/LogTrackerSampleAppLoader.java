package com.zone24x7.bi.sampleapp.startup;

import com.zone24x7.bi.logtracker.LogTrackerModule;
import com.zone24x7.bi.sampleapp.guice.modules.ControllerModule;
import com.zone24x7.bi.sampleapp.guice.modules.LoggerModule;
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
