package io.github.naviud.logtracker.actions.trackerproviders;

import org.apache.commons.lang3.StringUtils;
import play.mvc.Http;

/**
 * Interface to fetch the tracker id from sources
 *
 */
public abstract class TrackerIdFetcher {

    private TrackerIdFetcher nextTrackerIdFetcher;

    /**
     * Method to set setNextProvider tracker id provider
     *
     * @param next Next tracker id provider to set
     */
    public void setNextProvider(TrackerIdFetcher next) {
        this.nextTrackerIdFetcher = next;
    }

    /**
     * Method to get the tracker id based on the availability
     *
     * @param ctx HTTP context
     * @return tracker id
     */
    public String get(Http.Context ctx) {
        String id = null;
        TrackerIdFetcher trackerIdFetcher = this;
        while (trackerIdFetcher != null) {
            id = trackerIdFetcher.fetch(ctx);
            if(StringUtils.isEmpty(id)) {
                trackerIdFetcher = nextTrackerIdFetcher;
            } else {
                break;
            }
        }
        return id;
    }

    /**
     * Method to fetch the tracker id
     *
     * @param ctx Http.Context
     * @return tracker id
     */
    abstract String fetch(Http.Context ctx);
}
