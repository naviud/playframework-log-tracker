package com.logtracker.sampleapp.controllers;

import com.logtracker.LogTrackerLogger;
import com.logtracker.actions.LogTrackerAction;
import com.logtracker.annotations.LogTracker;
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