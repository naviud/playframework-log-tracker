package com.logtracker.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import com.logtracker.sampleapp.startup.LogTrackerSampleBootStrapper;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogTrackerSampleBootStrapper.class).asEagerSingleton();
    }
}
