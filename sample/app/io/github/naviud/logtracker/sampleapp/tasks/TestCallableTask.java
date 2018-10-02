package io.github.naviud.logtracker.sampleapp.tasks;

import io.github.naviud.logtracker.LogTrackerLogger;
import io.github.naviud.logtracker.annotations.LogTracker;
import io.github.naviud.logtracker.concurrent.LogTrackerCallable;

import javax.inject.Inject;

public class TestCallableTask extends LogTrackerCallable<String> {

    @Inject
    @LogTracker
    private static LogTrackerLogger logger;

    @Override
    protected String doCall() throws Exception {
        logger.info("callable test 123");
        return null;
    }
}
