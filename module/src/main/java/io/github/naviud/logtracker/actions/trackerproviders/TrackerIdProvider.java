package io.github.naviud.logtracker.actions.trackerproviders;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;

public class TrackerIdProvider implements Provider<TrackerIdFetcher> {

    private TrackerIdFetcher uuidBasedTrackerIdFetcher;
    private TrackerIdFetcher headerBasedTrackerIdFetcher;

    @Inject
    public TrackerIdProvider(@Named("UUIDBasedTrackerIdProvider") TrackerIdFetcher uuidBasedTrackerIdFetcher,
                             @Named("HeaderBasedTrackerIdFetcher") TrackerIdFetcher headerBasedTrackerIdFetcher) {
        this.uuidBasedTrackerIdFetcher = uuidBasedTrackerIdFetcher;
        this.headerBasedTrackerIdFetcher = headerBasedTrackerIdFetcher;
    }

    @Override
    public TrackerIdFetcher get() {
        headerBasedTrackerIdFetcher.setNextProvider(uuidBasedTrackerIdFetcher);
        uuidBasedTrackerIdFetcher.setNextProvider(null);

        return headerBasedTrackerIdFetcher;
    }
}
