package io.github.naviud.logtracker.sampleapp.tasks;

import com.google.inject.Singleton;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Singleton
public class TaskExecutorServiceImpl<T> implements TaskExecutorService<T> {

    private static ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool();

    /**
     * add a callable task to executor service
     *
     * @param task a callable
     * @return a future with T callable
     */
    @Override
    public Future<T> submitTask(Callable<T> task) {
        return EXECUTOR_SERVICE.submit(task);
    }
}