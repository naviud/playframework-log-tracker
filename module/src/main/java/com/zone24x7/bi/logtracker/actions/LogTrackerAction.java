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

package com.zone24x7.bi.logtracker.actions;

import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

import static java.util.UUID.randomUUID;

/**
 * Play action to enable log tracker
 *
 */
public class LogTrackerAction extends play.mvc.Action.Simple {

    /**
     * Overridden call method to integrate tracker id to the context
     *
     * @param ctx Context
     * @return
     */
    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        Http.Context.current().args.put("TrackerId", randomUUID().toString());
        return delegate.call(ctx);
    }
}