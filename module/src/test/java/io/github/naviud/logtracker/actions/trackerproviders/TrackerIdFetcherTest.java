package io.github.naviud.logtracker.actions.trackerproviders;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.powermock.reflect.Whitebox;
import play.mvc.Http;

import static org.mockito.Mockito.*;

public class TrackerIdFetcherTest {

    private TrackerIdFetcher trackerIdFetcher;
    private TrackerIdFetcher nextTrackerIdFetcher;
    private TrackerIdFetcher nextTrackerIdFetcher1;
    private Http.Context ctx;

    @Before
    public void setup() {
        trackerIdFetcher = mock(TrackerIdFetcher.class);
        nextTrackerIdFetcher = mock(TrackerIdFetcher.class);
        ctx = mock(Http.Context.class);
    }

    @Test
    public void should_return_a_tracker_id() {
        Whitebox.setInternalState(trackerIdFetcher, "nextTrackerIdFetcher", nextTrackerIdFetcher);
        when(trackerIdFetcher.fetch(ctx)).thenReturn("123");
        doCallRealMethod().when(trackerIdFetcher).get(ctx);
        Assert.assertEquals(trackerIdFetcher.get(ctx), "123");
    }

    @Test
    public void should_return_a_tracker_id_from_next() {
        Whitebox.setInternalState(trackerIdFetcher, "nextTrackerIdFetcher", nextTrackerIdFetcher);
        when(trackerIdFetcher.fetch(ctx)).thenReturn(null);
        when(nextTrackerIdFetcher.fetch(ctx)).thenReturn("456");
        doCallRealMethod().when(trackerIdFetcher).get(ctx);
        Assert.assertEquals(trackerIdFetcher.get(ctx), "456");
    }

    @Test
    public void should_return_a_null_from_next() {
        Whitebox.setInternalState(trackerIdFetcher, "nextTrackerIdFetcher", nextTrackerIdFetcher1);
        when(trackerIdFetcher.fetch(ctx)).thenReturn(null);
        when(nextTrackerIdFetcher.fetch(ctx)).thenReturn(null);
        doCallRealMethod().when(trackerIdFetcher).get(ctx);
        Assert.assertNull(trackerIdFetcher.get(ctx));
    }

    @Test
    public void should_set_the_next_provider() {
        doCallRealMethod().when(trackerIdFetcher).setNextProvider(nextTrackerIdFetcher);
        trackerIdFetcher.setNextProvider(nextTrackerIdFetcher);
        Assert.assertEquals(Whitebox.getInternalState(trackerIdFetcher, "nextTrackerIdFetcher"), nextTrackerIdFetcher);
    }

}
