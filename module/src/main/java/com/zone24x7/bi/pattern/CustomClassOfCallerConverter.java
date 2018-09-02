package com.zone24x7.bi.pattern;

import ch.qos.logback.classic.pattern.NamedConverter;
import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class CustomClassOfCallerConverter extends NamedConverter {

    protected String getFullyQualifiedName(ILoggingEvent event) {

        StackTraceElement[] cda = event.getCallerData();
        if (cda != null && cda.length > 0) {
            return cda[1].getClassName();
        } else {
            return CallerData.NA;
        }
    }
}