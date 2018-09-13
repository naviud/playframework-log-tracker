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

package io.github.naviud.logtracker.utils;

import io.github.naviud.logtracker.error.LogTrackerException;
import play.mvc.Http;

/**
 * Util class to hold Log tracker related util methods
 *
 */
public class LogTrackerUtil {

    private static ThreadLocal<Http.Context> context = new ThreadLocal<>();

    /**
     * Method to get the log tracker id
     *
     * @return Log tracker id
     * @throws LogTrackerException if error occurs when getting the tracker id
     */
    public static String getTrackerId() throws LogTrackerException {
        Http.Context ctx;
        try {
            ctx = Http.Context.current();
        } catch (RuntimeException e) {
            ctx = LogTrackerUtil.context.get();
        }

        try {
            return ctx.args.get("TrackerId").toString();
        } catch (Exception e) {
            throw new LogTrackerException("Error occurred when getting the context", e);
        }
    }

    /**
     * Method to set the Http context
     * The context will be set to the ThreadLocal
     *
     * @param ctx Context to set
     * @throws LogTrackerException
     */
    public static void setContext(Http.Context ctx) throws LogTrackerException {
        try {
            LogTrackerUtil.context.set(ctx);
        } catch (Exception e) {
            throw new LogTrackerException("Error occurred when transferring the context", e);
        }
    }

    /**
     * Clear the context
     *
     */
    public static void clearContext() {
        LogTrackerUtil.context.remove();
    }
}
