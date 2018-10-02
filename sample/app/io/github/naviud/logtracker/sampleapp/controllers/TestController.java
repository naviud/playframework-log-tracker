package io.github.naviud.logtracker.sampleapp.controllers;

import io.github.naviud.logtracker.LogTrackerLogger;
import io.github.naviud.logtracker.actions.LogTrackerAction;
import io.github.naviud.logtracker.annotations.LogTracker;
import io.github.naviud.logtracker.sampleapp.tasks.TaskExecutorService;
import io.github.naviud.logtracker.sampleapp.tasks.TestCallableTask;
import io.github.naviud.logtracker.sampleapp.tasks.TestTask;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.With;

import javax.inject.Inject;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class TestController extends Controller {

    @Inject
    @LogTracker
    private LogTrackerLogger logger;
    @Inject
    private Executor executor;
    @Inject
    private TaskExecutorService<String> taskExecutorService;

    @With(LogTrackerAction.class)
    public Result index() {
        try {
            taskExecutorService.submitTask(new TestCallableTask()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        logger.info("My test log message");
        return ok();
    }

}