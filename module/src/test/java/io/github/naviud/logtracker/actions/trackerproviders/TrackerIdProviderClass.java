package io.github.naviud.logtracker.actions.trackerproviders;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class TrackerIdProviderClass {

    private TrackerIdProvider trackerIdProvider;
    private TrackerIdFetcher uuidBasedTrackerIdFetcher;
    private TrackerIdFetcher headerBasedTrackerIdFetcher;

    @Before
    public void setup() {
        uuidBasedTrackerIdFetcher = mock(TrackerIdFetcher.class);
        headerBasedTrackerIdFetcher = mock(TrackerIdFetcher.class);

        trackerIdProvider = new TrackerIdProvider(uuidBasedTrackerIdFetcher, headerBasedTrackerIdFetcher);
    }

    @Test
    public void should_get_tracker_id_fetchers() {
        trackerIdProvider.get();

        verify(headerBasedTrackerIdFetcher, times(1)).setNextProvider(uuidBasedTrackerIdFetcher);
        verify(uuidBasedTrackerIdFetcher, times(1)).setNextProvider(null);
    }
}
