package io.github.naviud.logtracker.sampleapp.filters;

import akka.util.ByteString;
import io.github.naviud.logtracker.error.LogTrackerException;
import io.github.naviud.logtracker.utils.LogTrackerUtil;
import play.Logger;
import play.libs.streams.Accumulator;
import play.mvc.EssentialAction;
import play.mvc.EssentialFilter;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import static java.util.UUID.randomUUID;

/**
 * Class to generate access logs
 *
 */
public class AccessLogFilter extends EssentialFilter {
    private final Executor executor;
    private Logger.ALogger accessLogger = Logger.of("access");

    /**
     * Constructor for Access log filter
     *
     * @param executor executor
     */
    @Inject
    public AccessLogFilter(Executor executor) {
        super();
        this.executor = executor;
    }

    /**
     * Overriding the apply method.
     *
     * @param next Next Essential Action
     * @return The essential action
     */
    @Override
    public EssentialAction apply(EssentialAction next) {
        return EssentialAction.of(request -> {

            String uuid;
            try {
                uuid = LogTrackerUtil.getTrackerId();
            } catch (LogTrackerException e) {
                uuid = randomUUID().toString();
            }

            accessLogger.info("[REQUEST] UUID={} METHOD={} PATH={} QSTRING={} ORIGIN={} CORRELATION-ID={}",
                    uuid,
                    request.method(),
                    request.path(),
                    request.queryString().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.toString(e.getValue()))),
                    request.remoteAddress(),
                    request.getHeaders().get("correlation-id").orElse(null));

            long startTime = System.currentTimeMillis();
            Accumulator<ByteString, Result> accumulator = next.apply(request);
            String finalUuid = uuid;
            return accumulator.map(result -> {
                long endTime = System.currentTimeMillis();
                long requestTime = endTime - startTime;

                accessLogger.info("[RESPONSE] UUID={} METHOD={} PATH={} QSTRING={} ORIGIN={} STIME={}ms STATUS={} CORRELATION-ID={}",
                        finalUuid,
                        request.method(),
                        request.path(),
                        request.queryString().entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> Arrays.toString(e.getValue()))),
                        request.remoteAddress(),
                        requestTime,
                        result.status(),
                        request.getHeaders().get("correlation-id").orElse(null));

                return result;
            }, executor);
        });
    }
}