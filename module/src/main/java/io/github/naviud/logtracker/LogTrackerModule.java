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

import com.google.inject.Provides;
import com.google.inject.Singleton;
import io.github.naviud.logtracker.actions.trackerproviders.HeaderBasedTrackerIdFetcher;
import io.github.naviud.logtracker.actions.trackerproviders.TrackerIdFetcher;
import io.github.naviud.logtracker.actions.trackerproviders.TrackerIdProvider;
import io.github.naviud.logtracker.actions.trackerproviders.UuidBasedTrackerIdFetcher;
import play.api.Configuration;
import play.api.Environment;
import play.api.inject.Binding;
import play.api.inject.Module;
import scala.collection.Seq;

import javax.inject.Named;

/**
 * Class to represent LogTracker module
 *
 */
public class LogTrackerModule extends Module {

    /**
     * Method to bind contract with implementation
     *
     * @param environment Environment
     * @param configuration Configuration
     * @return Sequence of bindings
     */
    @Override
    public Seq<Binding<?>> bindings(Environment environment, Configuration configuration) {
        return seq(
                bind(LogTrackerLogger.class).to(LogTrackerLoggerImpl.class),
                bind(TrackerIdFetcher.class).qualifiedWith("UUIDBasedTrackerIdProvider").to(UuidBasedTrackerIdFetcher.class),
                bind(TrackerIdFetcher.class).qualifiedWith("HeaderBasedTrackerIdFetcher").to(HeaderBasedTrackerIdFetcher.class),
                bind(TrackerIdFetcher.class).toProvider(TrackerIdProvider.class)
        );
    }
}
