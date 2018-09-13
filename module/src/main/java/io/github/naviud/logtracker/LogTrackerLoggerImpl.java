/*
The MIT License (MIT)

Copyright (c) 2018 Udayanga Silva<udayanga.navindra@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package io.github.naviud.logtracker;

import io.github.naviud.logtracker.error.LogTrackerException;
import io.github.naviud.logtracker.utils.LogTrackerUtil;
import com.typesafe.config.Config;
import org.slf4j.MDC;
import play.Logger;

import javax.inject.Inject;

/**
 * Implementation of LogTrackerLogger to include log tracker id
 * in front of each log messages when possible
 *
 */
class LogTrackerLoggerImpl implements LogTrackerLogger {

    private Config config;

    private static final String LOGTRACKER_ERROR_VISIBLE_MESSAGE = "logtracker.error.visible.message";
    private static final String LOGTRACKER_ERROR_VISIBLE_DESC = "logtracker.error.visible.description";

    /**
     * Default constructor
     *
     */
    @Inject
    public LogTrackerLoggerImpl(Config config) {
        this.config = config;
        new Logger.ALogger(play.api.Logger.apply("application"));
    }

    /**
     * Method to log trace level logs
     *
     * @param message Message to log
     */
    public void trace(String message) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.trace(getMessage(message));

        MDC.clear();
    }

    /**
     * Method to log trace level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    public void trace(String message, Throwable error) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.trace(getMessage(message), error);

        MDC.clear();
    }

    /**
     * Method to log debug level logs
     *
     * @param message Message to log
     */
    public void debug(String message) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.trace(getMessage(message));

        MDC.clear();
    }

    /**
     * Method to log debug level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    public void debug(String message, Throwable error) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.debug(getMessage(message), error);

        MDC.clear();
    }

    /**
     * Method to log info level logs
     *
     * @param message Message to log
     */
    public void info(String message) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.info(getMessage(message));

        MDC.clear();
    }

    /**
     * Method to log info level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    public void info(String message, Throwable error) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.info(getMessage(message), error);

        MDC.clear();
    }

    /**
     * Method to log warn level logs
     *
     * @param message Message to log
     */
    public void warn(String message) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.warn(getMessage(message));

        MDC.clear();
    }

    /**
     * Method to log warn level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    public void warn(String message, Throwable error) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.warn(getMessage(message), error);

        MDC.clear();
    }

    /**
     * Method to log error level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    public void error(String message, Throwable error) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.error(getMessage(message), error);

        MDC.clear();
    }

    /**
     * Method to log error level logs
     *
     * @param message Message to log
     */
    public void error(String message) {
        MDC.put("class", Thread.currentThread().getStackTrace()[2].getClassName());
        MDC.put("method", Thread.currentThread().getStackTrace()[2].getMethodName());

        Logger.error(getMessage(message));

        MDC.clear();
    }

    /**
     * Private method to append log tracker id in front of log messages
     *
     * @param message Log message
     * @return Log tracker id appended log message
     */
    private String getMessage(String message) {
        String msg;
        try {
            msg = LogTrackerUtil.getTrackerId() + " : " + message;
        } catch (LogTrackerException e) {
            if (config.hasPath(LOGTRACKER_ERROR_VISIBLE_MESSAGE) && config.getBoolean(LOGTRACKER_ERROR_VISIBLE_MESSAGE)) {
                Logger.info("Log tracker id is not available at this moment");
            }
            if (config.hasPath(LOGTRACKER_ERROR_VISIBLE_DESC) && config.getBoolean(LOGTRACKER_ERROR_VISIBLE_DESC)) {
                Logger.trace("Log tracker id is not available at this moment", e);
            }
            msg = message;
        }
        return msg;
    }
}