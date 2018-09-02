package com.logtracker.sampleapp.startup;

import com.google.inject.Inject;
import play.inject.ApplicationLifecycle;

import java.util.concurrent.CompletableFuture;

public class LogTrackerSampleBootStrapper {
    @Inject
    LogTrackerSampleBootStrapper(ApplicationLifecycle applicationLifecycle) {
        //Any application startup related initialization needs to go here

        //Application shutdown tasks
        applicationLifecycle.addStopHook(() -> {
            //Any application shutdown related activities must be done here
            return CompletableFuture.completedFuture(null);
        });
    }
}
