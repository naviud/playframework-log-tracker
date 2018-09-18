package io.github.naviud.logtracker.actions.trackerproviders;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.mvc.Http;

import java.util.UUID;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({UUID.class, UuidBasedTrackerIdFetcher.class})
public class UuidBasedTrackerIdFetcherTest {

    UUID uuid;
    UuidBasedTrackerIdFetcher uuidBasedTrackerIdFetcher;
    Http.Context context;

    @Before
    public void setup() {
        PowerMockito.mockStatic(UUID.class);
        context = mock(Http.Context.class);
        uuidBasedTrackerIdFetcher = new UuidBasedTrackerIdFetcher();
    }

    @Test
    public void should_return_uuid() {
        BDDMockito.given(UUID.randomUUID().toString()).willReturn("123");
        Assert.assertEquals(uuidBasedTrackerIdFetcher.fetch(context), "123");
    }
}
