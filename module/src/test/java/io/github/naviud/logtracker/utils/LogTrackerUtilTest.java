package io.github.naviud.logtracker.utils;

import io.github.naviud.logtracker.error.LogTrackerException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;
import play.mvc.Http;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Http.Context.class})
public class LogTrackerUtilTest {

    private LogTrackerUtil logTrackerUtil;
    private Http.Context context;
    private Map<String, Object> args;

    @Before
    public void setup() {
        logTrackerUtil = new LogTrackerUtil();
        PowerMockito.mockStatic(Http.Context.class);
        args = new HashMap<>();
        args.put("TrackerId", "123");
    }

    @Test
    public void should_return_tracker_id() throws Exception {
        context = mock(Http.Context.class);
        context.args = args;

        PowerMockito.when(Http.Context.current()).thenReturn(context);

        Assert.assertEquals(LogTrackerUtil.getTrackerId(), "123");
    }

    @Test
    public void should_return_context_in_thread_local() throws Exception {
        context = mock(Http.Context.class);
        context.args = args;
        ThreadLocal<Http.Context> tlContext = new ThreadLocal<>();
        tlContext.set(context);
        Whitebox.setInternalState(LogTrackerUtil.class, "context", tlContext);

        PowerMockito.when(Http.Context.current()).thenThrow(RuntimeException.class);

        Assert.assertEquals(LogTrackerUtil.getTrackerId(), "123");
    }

    @Test(expected = LogTrackerException.class)
    public void should_throw_exception_when_trackerId_is_not_available() throws Exception {
        context = mock(Http.Context.class);
        context.args = null;
        ThreadLocal<Http.Context> tlContext = new ThreadLocal<>();
        tlContext.set(context);
        Whitebox.setInternalState(LogTrackerUtil.class, "context", tlContext);

        PowerMockito.when(Http.Context.current()).thenThrow(RuntimeException.class);

        LogTrackerUtil.getTrackerId();
    }


}
