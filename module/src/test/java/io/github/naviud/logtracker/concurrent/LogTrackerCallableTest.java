package io.github.naviud.logtracker.concurrent;

import io.github.naviud.logtracker.error.LogTrackerException;
import io.github.naviud.logtracker.utils.LogTrackerUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.internal.verification.VerificationModeFactory;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import play.Logger;
import play.mvc.Http;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LogTrackerUtil.class, LogTrackerCallable.class, Logger.class})
public class LogTrackerCallableTest {

    private LogTrackerCallable<String> logTrackerCallable;

    @Before
    public void setup() {
        logTrackerCallable = mock(LogTrackerCallable.class);
        PowerMockito.mockStatic(LogTrackerUtil.class);
        PowerMockito.mockStatic(Logger.class);
    }

    @Test
    public void should_call_the_method() throws Exception {
        PowerMockito.when(logTrackerCallable,"doCall").thenReturn("123");

        doCallRealMethod().when(logTrackerCallable).call();
        String returnVal = logTrackerCallable.call();

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.setContext(any(Http.Context.class));

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.clearContext();

        Assert.assertEquals(returnVal, "123");
    }

    @Test
    public void should_throw_log_tracker_exception_when_error_occurs_setting_context() throws Exception {
        PowerMockito.when(LogTrackerUtil.class,"setContext", any(Http.Context.class)).thenThrow(LogTrackerException.class);

        doCallRealMethod().when(logTrackerCallable).call();
        String returnVal = logTrackerCallable.call();

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        Logger.warn(anyString(), any(LogTrackerException.class));

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.clearContext();

        Assert.assertNull(returnVal);
    }

    @Test
    public void should_throw_exception_when_error_occurs_calling_doCall() throws Exception {
        PowerMockito.when(logTrackerCallable,"doCall").thenThrow(Exception.class);

        doCallRealMethod().when(logTrackerCallable).call();
        String returnVal = logTrackerCallable.call();

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        Logger.warn(anyString(), any(Exception.class));

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.clearContext();

        Assert.assertNull(returnVal);
    }
}
