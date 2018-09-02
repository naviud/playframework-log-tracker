package com.zone24x7.bi.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import com.zone24x7.bi.sampleapp.startup.LogTrackerSampleBootStrapper;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogTrackerSampleBootStrapper.class).asEagerSingleton();
    }
}
