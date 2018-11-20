<p align="center">
<a href="https://travis-ci.org/naviud/playframework-log-tracker">
    <img src="https://api.travis-ci.org/naviud/playframework-log-tracker.svg?branch=master" alt="travis build" height="18">
</a>
<a href="https://github.com/naviud/playframework-log-tracker/blob/master/LICENSE">
    <img src="https://img.shields.io/badge/license-MIT-blue.svg?style=flat" alt="license" height="18">
</a>
</p>
   
> LogTracker is a logger module done for Play framework which prepends a unique tracker id for the log messages that are generated for a particular request. Tracker id can be passed from a HTTP header or will be selected randomly when a request is initiated.

Table of Content
----------------
* [Introduction](#intro)
* [Configure](#config)
* [How to use](#use)
* [How to contribute](#contribute)

<a name="intro"/>

## &#x1F680; Introduction

When analyzing application and access logs in your application via a log monitoring tool like Splunk, there can be a need to cluster log messages which are generated for a particular request. Since all the logs are appended together in a way that cannot be clustered, it would be difficult when getting logs which are part of a particular request.  

This LogTracker logger module will help to resolve such problems which can happen in your Play project.

```
2018-09-19 11:36:23.337 +0530 - [application-akka.actor.default-dispatcher-2] [TRACE] - from com.xyz.bi.das.controllers.actions.V2PropensityParameterValidatorAction.call 
f4a0051f-1f6a-4487-8327-2f83a363a66d : V2 endpoint -> ATG Id : 0 | VIS Id : null  | MCM Id : null  | KNOWN ALG Id : null | UNKNOWN ALG Id : null | CCP : null | CALLER : null

2018-09-19 11:36:23.337 +0530 - [application-akka.actor.default-dispatcher-2] [INFO] - from com.xyz.bi.das.algorithms.samplers.RandomNumberSampler.getAnAlgorithmFromDistribution 
f4a0051f-1f6a-4487-8327-2f83a363a66d : Algorithm distribution is null or empty

2018-09-19 11:36:23.339 +0530 - [application-akka.actor.default-dispatcher-2] [TRACE] - from com.xyz.bi.das.cache.persistent.HbaseConfiguredTablePersistentCache.getValueFromHbaseTable 
f4a0051f-1f6a-4487-8327-2f83a363a66d : Persistent cache level call initiating.. Table : recommendations_propensity | Key : 0##recommendations_propensity##rec##204 | Id : 0 | Family : rec | Qualifier : 204

2018-09-19 11:36:23.339 +0530 - [application-akka.actor.default-dispatcher-2] [INFO] - from com.xyz.bi.das.dao.HBaseConnectionRetryBehavior.shouldAttemptToConnect 
f4a0051f-1f6a-4487-8327-2f83a363a66d : Attempting to establish the connection...

2018-09-19 11:36:23.339 +0530 - [pool-16-thread-2] [TRACE] - from com.xyz.bi.das.dao.resultproviders.HbaseResultProviderWithOptionalParamTask.doCall 
f4a0051f-1f6a-4487-8327-2f83a363a66d : BigTable cache level call initiating.. Table : recommendations_propensity | Key : 0 | Family : rec | Qualifier : 204

2018-09-19 11:36:32.939 +0530 - [application-akka.actor.default-dispatcher-2] [INFO] - from com.xyz.bi.das.dao.HbaseDasConnection.getRecords 
f4a0051f-1f6a-4487-8327-2f83a363a66d : Time spent(in ms) to fetch record from BigTable Table : recommendations_propensity | Key : 0 | Family : rec | Qualifier : 204 -> 9600.401804

2018-09-19 11:36:32.940 +0530 - [application-akka.actor.default-dispatcher-2] [INFO] - from com.xyz.bi.das.cache.persistent.BasePersistentCache.addToCacheAndReturnValue 
f4a0051f-1f6a-4487-8327-2f83a363a66d : An empty/null value was received from BigTable and not set to cache. Data Category : recommendations_propensity | Id : 0##recommendations_propensity##rec##204

2018-09-19 11:36:32.940 +0530 - [application-akka.actor.default-dispatcher-2] [TRACE] - from com.xyz.bi.das.data.operations.fetchers.AtgIdPropensityFetcher.retrieve 
f4a0051f-1f6a-4487-8327-2f83a363a66d : No propensities for ATG Id : 0

2018-09-19 11:36:32.942 +0530 - [application-akka.actor.default-dispatcher-2] [INFO] - from com.xyz.bi.das.controllers.PropensityV2Controller.getPropensityDataForV2 
f4a0051f-1f6a-4487-8327-2f83a363a66d : Time spent(in ms) to serve customer propensity for ATG Id : 0 | VIS Id : null | MCM Id : null | Used Customer Id : null | Known Algorithm Id : null | Anonymous Algorithm Id : null | Used Algorithm Id : null | CCP : <NO_CCP> | Caller : null | Response cycle : 000148151001017018021024028125 -> 9604.894780 with the size(in KB) -> 0.069336
```

<a name="config"/>

## &#x1F680; Configure


There are very easy steps to incorporate this logger to your project.

### &#x1F488; Step 1 : Import the library dependency to your project

Add the following dependency resolver to the `libraryDependencies` sequence in your `build.sbt` file to import the module to your project.

```Scala
"io.github.naviud" % "log-tracker" % "<VERSION>"
```

&#x1F4D3;
Current stable version is `0.0.9`

### &#x1F488; Step 2 : Register the  module

To register the module, add following line to the `application.conf` file.

```Java
play.modules.enabled += "io.github.naviud.logtracker.LogTrackerModule"
```

### &#x1F488; Step 3 : Add a logger provider

This helps to inject the `LogTrackerLogger` instances wherever you want. To create the injector, use `@Provides` annotation in Guice.

```Java
@Provides
@LogTracker
LogTrackerLogger provideLogger(LogTrackerLogger logTrackerLogger) {
    return logTrackerLogger;
}
```
This log provider needs to be included to a class which extends a Play `AbstractModule` and should be bound to the `GuiceApplicationLoader`.

`@LogTracker` annotation comes with this Play module itself and, this needs to be included anywhere `LogTrackerLogger` is being used.

### &#x1F488; Step 4 : Use the logger injector

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

&#x1F4D3;
If you want to use a tracker id which is passed to your microservice as a HTTP header, you can just use it in your log messages. For that, what you need to do is just specify the header name which contains the tracker id to the `logtracker.tracker.header` configuration key.

`logtracker.tracker.header = "correlation-id"`

If 
- The `logtracker.tracker.header` key is not present in the `application.conf` or 
- Specified header name to the `logtracker.tracker.header` is not available in the HTTP request or 
- Any value not present in the specified header

then a random log tracker id(UUID) will be used.

## Optional Steps

### &#x1F390; Extend the LogTrackerCallable

This facilitates to track logs when `Callable` tasks are being used to offload tasks. If `Callable` tasks are in use in your code, you need to use `LogTrackerCallable` instead.

```Java
public class MyCallableTask extends LogTrackerCallable<String> {
    
    @Override
    public String doCall() throws MyException {
        //do your own logic
        return "testString";
    }
}
```
This is an abstract class and you need to place your logic in the `doCall()` method. Its' return type depends on the type which is declared in the `LogTrackerEnabledTask`.

### &#x1F390; Extend the LogTrackerRunnable

This facilitates to track logs when `Runnable` tasks are being used to offload tasks. If `Runnable` tasks are in use in your code, you need to use `LogTrackerRunnable` instead.

```Java
public class MyRunnableTask extends LogTrackerRunnable {
    
    @Override
    public vod doRun() throws MyException {
        //do your own logic
    }
}
```
This is an abstract class and you need to place your logic in the `doRun()` method. Its' return type depends on the type which is declared in the `LogTrackerEnabledTask`.


### &#x1F390; Use the tracker id

It's also possible to use the tracker id for any other use case in your project.

Ex: If you want to you the tracker id in any other logger in order to link all the logs together, the following might be useful.

```Java
public class OtherLoggerTest {
    private Logger.ALogger otherLogger = Logger.of("other-logger");
    
    public void testMethod() {
        otherLogger.info(LogTrackerUtil.getTrackerId() + " " + "my log message");
    }
}
```

From `LogTrackerUtil.getTrackerId()` static method, you can get the current tracker id.

### &#x1F390; Log patterns

If you want to include name of class, method to the logs when using this module, you need to use following switches in the patterns of your `logger.xml` file.

For class : `%X{class}`

For method : `%X{method}`

Ex:
```xml
<pattern>
    %date{yyyy-MM-dd HH:mm:ss.SSS ZZZZ} - [%thread] [%level] - from %X{class}.%X{method} %n%message%n%xException%n
</pattern> 
```

&#x1F4D3;
If you already use `%class`, `%method` switches in your `logger.xml` file, they no longer will work, instead modify them to the above mentioned switches.

### &#x1F390; Log errors

If you want to log error messages and stack traces which are generated by this module for debugging purposes, add the following config entries to your `application.conf` file.

_To log error messages_

`logtracker.error.visible.message = true`

_To log stack traces_

`logtracker.error.visible.description = true`

&#x1F4D3;
If above config entries are not added, it's set to `false` by default.

<a name="use"/>

## &#x1F680; How to use

A sample Play application has been integrated with the LogTracker is in the repository which illustrates how to use this.

<a name="contribute"/>

## &#x1F680; How to contribute

To contribute to this project, first setup the project in your local environment.

Submit issues, pull requests or contributions would be appreciated.
