package io.github.naviud.logtracker.sampleapp.tasks;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public interface TaskExecutorService<T> {
    /**
     * method to add a task to executor service
     * @param task
     * @return A unique id for the task
     */
    Future<T> submitTask(Callable<T> task);
}