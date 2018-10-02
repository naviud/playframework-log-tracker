package io.github.naviud.logtracker.actions.trackerproviders;

import com.typesafe.config.Config;
import org.apache.commons.lang3.StringUtils;
import play.mvc.Http;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Implementation of TrackerIdFetcher for HTTP header based
 *
 */
@Singleton
public class HeaderBasedTrackerIdFetcher extends TrackerIdFetcher {

    private Config config;

    private static final String LOGTRACKER_TRACKER_PROVIDER_HEADER = "logtracker.tracker.header";

    @Inject
    public HeaderBasedTrackerIdFetcher(Config config) {
        this.config = config;
    }

    /**
     * Method to fetch the tracker id
     *
     * @return tracker id
     */
    @Override
    public String fetch(Http.Context ctx) {
        if
        (
                config.hasPath(LOGTRACKER_TRACKER_PROVIDER_HEADER) &&
                ctx.request().getHeaders().get(config.getString(LOGTRACKER_TRACKER_PROVIDER_HEADER)).isPresent()
        ) {
            return ctx.request()
                    .getHeaders()
                    .get(config.getString(LOGTRACKER_TRACKER_PROVIDER_HEADER))
                    .filter(StringUtils::isNotEmpty)
                    .get();
        }
        return null;
    }
}
