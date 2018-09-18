package io.github.naviud.logtracker.actions.trackerproviders;

import com.google.inject.Singleton;
import play.mvc.Http;

import static java.util.UUID.randomUUID;

/**
 * Implementation of TrackerIdFetcher for UUID based
 *
 */
@Singleton
public class UuidBasedTrackerIdFetcher extends TrackerIdFetcher {

    /**
     * Method to fetch the tracker id
     *
     * @return tracker id
     */
    @Override
    public String fetch(Http.Context ctx) {
        return randomUUID().toString();
    }
}
