package io.github.naviud.logtracker.sampleapp.controllers;

import io.github.naviud.logtracker.LogTrackerLogger;
import io.github.naviud.logtracker.actions.LogTrackerAction;
import io.github.naviud.logtracker.annotations.LogTracker;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;

public class TestController extends Controller {

    @Inject
    @LogTracker
    private LogTrackerLogger logger;

    @With(LogTrackerAction.class)
    public Result index() {
        logger.info("My test log message");
        return ok();
    }

}