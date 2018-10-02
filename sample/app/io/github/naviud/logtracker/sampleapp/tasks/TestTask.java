package io.github.naviud.logtracker.sampleapp.tasks;

import io.github.naviud.logtracker.LogTrackerLogger;
import io.github.naviud.logtracker.annotations.LogTracker;
import io.github.naviud.logtracker.concurrent.LogTrackerRunnable;

import javax.inject.Inject;

public class TestTask extends LogTrackerRunnable {

    @Inject
    @LogTracker
    private static LogTrackerLogger logger;

    @Override
    protected void doRun() throws Exception {
        logger.info("test 123");
    }
}
