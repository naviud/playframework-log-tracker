package com.zone24x7.bi.sampleapp.startup;

import com.zone24x7.bi.logtracker.LogTrackerLogger;
import play.http.HttpErrorHandler;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;

import static play.mvc.Results.notFound;

public class LogTrackerHttpErrorHandler implements HttpErrorHandler {

    @Inject
    private LogTrackerLogger logger;

    /**
     * Overrided client error handler
     *
     * @param requestHeader headers that came in the request
     * @param statusCode    HTTP status code for the request
     * @param message       error message
     * @return              client error promise
     */
    @Override
    public CompletableFuture<Result> onClientError(Http.RequestHeader requestHeader, int statusCode, String message) {
        if(statusCode == play.mvc.Http.Status.NOT_FOUND) {
            // If the status code is 404 - Not Found then we need to send a custom Not Found error message
            return CompletableFuture.completedFuture(
                    notFound("Not Found.")
            );
        } else {
            // Otherwise simply return the original error
            return CompletableFuture.completedFuture(
                    Results.status(statusCode, "A client error occurred: " + message)
            );
        }
    }

    /**
     * Overrided server error handler
     *
     * @param requestHeader headers that came in the request
     * @param exception     server side exception
     * @return
     */
    @Override
    public CompletableFuture<Result> onServerError(Http.RequestHeader requestHeader, Throwable exception) {
        logger.error("Caught a global server error", exception);
        return CompletableFuture.completedFuture(
                Results.internalServerError("A server error occurred")
        );
    }
}
