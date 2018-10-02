package io.github.naviud.logtracker.sampleapp.guice.modules;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import io.github.naviud.logtracker.sampleapp.startup.LogTrackerSampleBootStrapper;
import io.github.naviud.logtracker.sampleapp.tasks.TaskExecutorService;
import io.github.naviud.logtracker.sampleapp.tasks.TaskExecutorServiceImpl;

public class ControllerModule extends AbstractModule {

    @Override
    protected void configure() {

        bind(new TypeLiteral<TaskExecutorService<String>>() {
        }).to(new TypeLiteral<TaskExecutorServiceImpl<String>>() {
        });

        bind(LogTrackerSampleBootStrapper.class).asEagerSingleton();
    }
}
