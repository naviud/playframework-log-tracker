package io.github.naviud.logtracker.actions.trackerproviders;

import com.typesafe.config.Config;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import play.mvc.Http;

import java.util.Optional;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class HeaderBasedTrackerIdFetcherTest {

    private Config config;
    private Http.Context ctx;
    private HeaderBasedTrackerIdFetcher headerBasedTrackerIdFetcher;
    private Http.Headers headers;

    @Before
    public void setup() {
        config = mock(Config.class);
        ctx = mock(Http.Context.class, RETURNS_DEEP_STUBS);
        headers = mock(Http.Headers.class);
        when(ctx.request().getHeaders()).thenReturn(headers);

        headerBasedTrackerIdFetcher = new HeaderBasedTrackerIdFetcher(config);
    }

    @Test
    public void should_return_the_header_value_when_exists() {
        when(config.hasPath(anyString())).thenReturn(true);
        when(headers.get(anyString())).thenReturn(Optional.of("123"));
        when(ctx.request().getHeaders().get(anyString()).filter(String::isEmpty)).thenReturn(Optional.of("123"));

        Assert.assertEquals(headerBasedTrackerIdFetcher.fetch(ctx), "123");
    }

    @Test
    public void should_return_null_when_header_is_not_configured_in_config() {
        when(config.hasPath(anyString())).thenReturn(false);

        Assert.assertNull(headerBasedTrackerIdFetcher.fetch(ctx));
    }

    @Test
    public void should_return_null_when_header_is_configured_in_config_but_header_does_not_exist() {
        when(config.hasPath(anyString())).thenReturn(true);
        when(headers.get(anyString())).thenReturn(Optional.ofNullable(null));

        Assert.assertNull(headerBasedTrackerIdFetcher.fetch(ctx));
    }
}
