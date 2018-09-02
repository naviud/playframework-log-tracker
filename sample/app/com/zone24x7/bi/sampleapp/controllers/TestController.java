package com.zone24x7.bi.sampleapp.controllers;

import com.zone24x7.bi.logtracker.LogTrackerLogger;
import com.zone24x7.bi.logtracker.actions.LogTrackerAction;
import com.zone24x7.bi.logtracker.annotations.LogTracker;
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