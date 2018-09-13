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

package io.github.naviud.logtracker.concurrent;

import io.github.naviud.logtracker.error.LogTrackerException;
import io.github.naviud.logtracker.utils.LogTrackerUtil;
import play.Logger;
import play.mvc.Http;

import java.util.concurrent.Callable;

/**
 * Abstract callable to enable with log tracker
 *
 * @param <T>
 */
public abstract class LogTrackerCallable<T> implements Callable<T> {

    protected final Http.Context context;

    /**
     * Default constructor
     *
     */
    public LogTrackerCallable() {
        this.context = Http.Context.current();
    }

    /**
     * Overridden call method of the callable interface
     *
     * @return <T>
     * @throws Exception
     */
    @Override
    public T call() throws Exception {
        try {
            LogTrackerUtil.setContext(context);
            return doCall();
        } catch (LogTrackerException e) {
            Logger.warn("Context transfer failed", e);
        } catch (Exception e) {
                Logger.warn("Callable exception occurred", e);
        } finally {
            LogTrackerUtil.clearContext();
        }
        return null;
    }

    /**
     * Method to be overridden to include task related logic
     *
     * @return T
     * @throws Exception
     */
    protected abstract T doCall() throws Exception;
}