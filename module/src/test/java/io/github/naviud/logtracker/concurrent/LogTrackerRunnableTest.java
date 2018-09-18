package io.github.naviud.logtracker.concurrent;

import io.github.naviud.logtracker.error.LogTrackerException;
import io.github.naviud.logtracker.utils.LogTrackerUtil;
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
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;

@RunWith(PowerMockRunner.class)
@PrepareForTest({LogTrackerUtil.class, LogTrackerRunnable.class, Logger.class})
public class LogTrackerRunnableTest {

    private LogTrackerRunnable logTrackerRunnable;

    @Before
    public void setup() {
        logTrackerRunnable = mock(LogTrackerRunnable.class);
        PowerMockito.mockStatic(LogTrackerUtil.class);
        PowerMockito.mockStatic(Logger.class);
    }

    @Test
    public void should_run_the_method() throws Exception {
        doCallRealMethod().when(logTrackerRunnable).run();
        logTrackerRunnable.run();

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.setContext(any(Http.Context.class));

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.clearContext();
    }

    @Test
    public void should_throw_log_tracker_exception_when_error_occurs_setting_context() throws Exception {
        PowerMockito.when(LogTrackerUtil.class,"setContext", any(Http.Context.class)).thenThrow(LogTrackerException.class);

        doCallRealMethod().when(logTrackerRunnable).run();
        logTrackerRunnable.run();

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        Logger.warn(anyString(), any(LogTrackerException.class));

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.clearContext();
    }

    @Test
    public void should_throw_exception_when_error_occurs_calling_doCall() throws Exception {
        PowerMockito.when(logTrackerRunnable,"doRun").thenThrow(Exception.class);

        doCallRealMethod().when(logTrackerRunnable).run();
        logTrackerRunnable.run();

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        Logger.warn(anyString(), any(Exception.class));

        PowerMockito.verifyStatic(VerificationModeFactory.times(1));
        LogTrackerUtil.clearContext();
    }

}
