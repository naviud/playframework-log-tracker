READ ME
======
LogTracker is a logger module done for Play framework which prepends a unique UUID(tracker id) for the log messages that are generated for a particular request. Tracker id will be selected randomly when a request is initiated.

Table of Content
----------------
* [Introduction](#intro)
* [Configure](#config)
* [How to use](#use)
* [How to contribute](#contribute)

<a name="intro"/>

## Introduction

When analyzing application and access logs in your application, there can be a need to see the log message flow. Since all the logs are appended together, thus it would be difficult select logs which are part of a particular request.  

This LogTracker logger module will help to resolve such problem which can happen in your Play project.

```
[trace] 2018-08-28 14:42:51.584 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : V2 endpoint -> ATG Id : 2254029711161505 | VIS Id : null  | MCM Id : null  | KNOWN ALG Id : null | UNKNOWN ALG Id : null | CCP : null | CALLER : null

[info] 2018-08-28 14:42:51.629 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Attempting to establish the connection...

[trace] 2018-08-28 14:42:51.644 +0530 - [pool-16-thread-1] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : BigTable cache level call initiating.. Table : rec_switch_table | Key : recommendations_propensity | Family : tables | Qualifier : active

[info] 2018-08-28 14:42:56.137 +0530 - [pool-17-thread-1] - from com.logtracker.LogTrackerLoggerImpl.getMessage 
Log tracker id is not available at this moment

[info] 2018-08-28 14:43:00.315 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Time spent(in ms) to fetch record from BigTable Table : rec_switch_table | Key : recommendations_propensity -> 8684.068870

[trace] 2018-08-28 14:43:00.321 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Persistent cache level call initiating.. Table : recommendations_propensity | Key : 2254029711161505##recommendations_propensity##rec##176 | Id : 2254029711161505 | Family : rec | Qualifier : 176

[info] 2018-08-28 14:43:00.321 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Attempting to establish the connection...

[trace] 2018-08-28 14:43:00.322 +0530 - [pool-16-thread-1] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : BigTable cache level call initiating.. Table : recommendations_propensity | Key : 2254029711161505 | Family : rec | Qualifier : 176

[info] 2018-08-28 14:43:09.729 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Time spent(in ms) to fetch record from BigTable Table : recommendations_propensity | Key : 2254029711161505 | Family : rec | Qualifier : 176 -> 9407.930667

[info] 2018-08-28 14:43:09.731 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : An empty/null value was received from BigTable and not set to cache. Data Category : recommendations_propensity | Id : 2254029711161505##recommendations_propensity##rec##176

[trace] 2018-08-28 14:43:09.732 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Persistent cache level call initiating.. Table : recommendations_propensity | Key : 2254029711161505##recommendations_propensity##rec##176 | Id : 2254029711161505 | Family : rec | Qualifier : 176

[info] 2018-08-28 14:43:09.733 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Attempting to establish the connection...

[trace] 2018-08-28 14:43:09.733 +0530 - [pool-16-thread-1] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : BigTable cache level call initiating.. Table : recommendations_propensity | Key : 2254029711161505 | Family : rec | Qualifier : 176

[info] 2018-08-28 14:43:18.737 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Time spent(in ms) to fetch record from BigTable Table : recommendations_propensity | Key : 2254029711161505 | Family : rec | Qualifier : 176 -> 9004.960491

[info] 2018-08-28 14:43:18.738 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : An empty/null value was received from BigTable and not set to cache. Data Category : recommendations_propensity | Id : 2254029711161505##recommendations_propensity##rec##176

[trace] 2018-08-28 14:43:18.739 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.debug 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : No propensities for ATG Id : 2254029711161505

[info] 2018-08-28 14:43:18.778 +0530 - [application-akka.actor.default-dispatcher-4] - from com.logtracker.LogTrackerLoggerImpl.info 
8d84803a-da8e-4f51-b96e-57c9fc36f480 : Time spent(in ms) to serve customer propensity for ATG Id : 2254029711161505 | VIS Id : null | MCM Id : null | Used Customer Id : null | Known Algorithm Id : null | Anonymous Algorithm Id : null | Used Algorithm Id : null | CCP : <NO_CCP> | Caller : null | Response cycle : 000148157001002003006009013017018021024028125 -> 27191.436406 with the size(in KB) -> 0.069336

[info] 2018-08-28 14:43:26.136 +0530 - [pool-17-thread-1] - from com.logtracker.LogTrackerLoggerImpl.getMessage 
Log tracker id is not available at this moment
```

<a name="config"/>

## Configure


There are very easy steps to incorporate this logger to your project.

### Step 1 : Import the library dependency to your project

Add the following dependency resolver to the `libraryDependencies` sequence in your `build.sbt` file to import the the module to your project.

```Scala
"io.github.naviud" % "log-tracker" % "0.0.2-SNAPSHOT"
```

### Step 2 : Register the  module

To register the module, add following line to the `application.conf` file.

```Java
play.modules.enabled += "io.github.naviud.logtracker.LogTrackerModule"
```

### Step 3 : Add a logger provider

This helps to inject the `LogTrackerLogger` instances wherever you want. To create the injector, use `@Provides` annotation in Guice.

```Java
@Provides
@LogTracker
LogTrackerLogger provideLogger(LogTrackerLogger logTrackerLogger) {
    return logTrackerLogger;
}
```
This log provider needs to include to a class which extends a Play `AbstractModule` and should be bound to the `GuiceApplicationLoader`.

`@LogTracker` annotation comes with this Play module itself and, this needs to use in every where you use the `LogTrackerLogger`.

### Step 4 : Use the logger injector

In order to use the `LogTrackerLogger`, add the `@LogTracker` annotation  to the member variable of type `LogTrackerLogger` in your classes.

```Java
public class TestController extends Controller {

    @Inject
    @LogTracker
    private static LogTrackerLogger logger;

    @With(LogTrackerAction.class)
    public Result index() {
        logger.info("My test log message");
        return ok();
    }

}
```

To initiate to track your logs, it's necessary to decorate your controller methods with `LogTrackerAction` Play action. This initiates the random log tracker id(UUID) and propagates throughout HTTP context.  

**Note :**
If you want to use a tracker id which passed to your microservice as a HTTP header, you can just use it in your log messages. For that, what you need to do is just specify the header name which contains the tracker id to the `logtracker.tracker.header` configuration key.

`logtracker.tracker.header = "correlation-id"`

If 
- The `logtracker.tracker.header` key is not present in the `application.conf` or 
- Specified header name to the `logtracker.tracker.header` is not available in the HTTP request or 
- Any value does not contain in the specified header

then a random log tracker id(UUID) will be used.

### Step 5 : Extend the LogTrackerCallable

This is an optional step where to facilitate the track logs when `Callable` tasks are being used to offload tasks. If `Callable` tasks are in use in your code, you need to use `LogTrackerCallable` instead of them.

```Java
public class MyCallableTask extends LogTrackerCallable<String> {
    
    @Override
    public String doCall() throws MyException {
        //do your own logic
        return "testString";
    }
}
```
This is an abstract class and you need to place your logic in the `doCall()` method. Its' return type will depends on the type which declares in the `LogTrackerEnabledTask`.

### Step 6 : Extend the LogTrackerRunnable

This is another an optional step where to facilitate the track logs when `Runnable` tasks are being used to offload tasks. If `Runnable` tasks are in use in your code, you need to use `LogTrackerRunnable` instead of them.

```Java
public class MyRunnableTask extends LogTrackerRunnable {
    
    @Override
    public vod doRun() throws MyException {
        //do your own logic
    }
}
```
This is an abstract class and you need to place your logic in the `doRun()` method. Its' return type will depends on the type which declares in the `LogTrackerEnabledTask`.


### Step 7 : Use the tracker id

When you want to use the tracker id for any other thing in your project, you have a way to do that.

Ex: If you want to you the tracker id in any other logger in order to link all the logs together, following might be useful.

```Java
public class OtherLoggerTest {
    private Logger.ALogger otherLogger = Logger.of("other-logger");
    
    public void testMethod() {
        otherLogger.info(LogTrackerUtil.getTrackerId() + " " + "my log message");
    }
}
```

From `LogTrackerUtil.getTrackerId()` static method, you can get the current tracker id.

### Step 8 : Log patterns

If you want to include name of class, method to the logs when using this module, you need to use following switches in the patterns of your `logger.xml` file.

For class : `%X{class}`

For method : `%X{method}`

Ex:
```xml
<pattern>
    %date{yyyy-MM-dd HH:mm:ss.SSS ZZZZ} - [%thread] [%level] - from %X{class}.%X{method} %n%message%n%xException%n
</pattern> 
```

### Step 9 : Log errors

If you want to log error messages and stack traces which generate by this module for debugging purposes, add following config entries to your `application.conf` file.

_To log error messages_

`logtracker.error.visible.message = true`

_To log stack traces_

`logtracker.error.visible.description = true`

**Note :**
If above config entries are not added, it's considered that they set to `false`.

<a name="use"/>

## How to use

A sample Play application integrated with the LogTracker is in the repository which illustrates how to use this.

<a name="contribute"/>

## How to contribute

To contribute to this project, first setup the project in your local environment.

Submit issues, pull requests or contributions would be appreciated.
