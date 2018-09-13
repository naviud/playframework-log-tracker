package io.github.naviud.logtracker.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import io.github.naviud.logtracker.sampleapp.startup.LogTrackerSampleBootStrapper;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(LogTrackerSampleBootStrapper.class).asEagerSingleton();
    }
}
