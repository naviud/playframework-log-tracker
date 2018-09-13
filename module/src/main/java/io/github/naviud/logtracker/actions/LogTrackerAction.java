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

package io.github.naviud.logtracker.actions;

import com.typesafe.config.Config;
import org.apache.commons.lang3.StringUtils;
import play.mvc.Http;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

import static java.util.UUID.randomUUID;

/**
 * Play action to enable log tracker
 *
 */
public class LogTrackerAction extends play.mvc.Action.Simple {

    private Config config;

    private static final String LOGTRACKER_TRACKER_PROVIDER_HEADER = "logtracker.tracker.header";


    @Inject
    public LogTrackerAction(Config config) {
        this.config = config;
    }

    /**
     * Overridden call method to integrate tracker id to the context
     *
     * @param ctx Context
     * @return
     */
    @Override
    public CompletionStage<Result> call(Http.Context ctx) {
        String trackerId;
        if (config.hasPath(LOGTRACKER_TRACKER_PROVIDER_HEADER)) {
            String header =  config.getString(LOGTRACKER_TRACKER_PROVIDER_HEADER);
            trackerId = ctx.request().getHeaders().get(header).filter(StringUtils::isNotEmpty).orElse(randomUUID().toString());
        } else {
            trackerId = randomUUID().toString();
        }
        Http.Context.current().args.put("TrackerId", trackerId);
        return delegate.call(ctx);
    }
}