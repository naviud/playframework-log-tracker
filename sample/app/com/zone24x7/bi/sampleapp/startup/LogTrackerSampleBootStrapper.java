package com.zone24x7.bi.sampleapp.startup;

import com.google.inject.Inject;
import org.slf4j.MDC;
import play.inject.ApplicationLifecycle;
import ch.qos.logback.core.ConsoleAppender;

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
