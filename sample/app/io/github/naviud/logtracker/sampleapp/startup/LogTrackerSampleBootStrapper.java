package io.github.naviud.logtracker.sampleapp.startup;

import com.google.inject.Inject;
import io.github.naviud.logtracker.sampleapp.tasks.TestTask;
import play.inject.ApplicationLifecycle;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class LogTrackerSampleBootStrapper {

    @Inject
    LogTrackerSampleBootStrapper(ApplicationLifecycle applicationLifecycle, Executor executor) {
        //Any application startup related initialization needs to go here

        executor.execute(new TestTask());

        //Application shutdown tasks
        applicationLifecycle.addStopHook(() -> {
            //Any application shutdown related activities must be done here
            return CompletableFuture.completedFuture(null);
        });
    }
}
