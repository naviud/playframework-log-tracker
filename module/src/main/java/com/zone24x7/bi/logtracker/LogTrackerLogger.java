/*
The MIT License (MIT)

Copyright (c) 2018 Udayanga Silva<udayanga.navindra@gmail.com>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package com.zone24x7.bi.logtracker;

/**
 * Class to represent LogTrackerLogger contract
 *
 */
public interface LogTrackerLogger {

    /**
     * Method to log trace level logs
     *
     * @param message Message to log
     */
    void trace(String message);

    /**
     * Method to log trace level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    void trace(String message, Throwable error);

    /**
     * Method to log debug level logs
     *
     * @param message Message to log
     */
    void debug(String message);

    /**
     * Method to log trace level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    void debug(String message, Throwable error);

    /**
     * Method to log info level logs
     *
     * @param message Message to log
     */
    void info(String message);

    /**
     * Method to log info level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    void info(String message, Throwable error);

    /**
     * Method to log warn level logs
     *
     * @param message Message to log
     */
    void warn(String message);

    /**
     * Method to log warn level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    void warn(String message, Throwable error);

    /**
     * Method to log error level logs
     *
     * @param message Message to log
     * @param error Error to log
     */
    void error(String message, Throwable error);

    /**
     * Method to log error level logs
     *
     * @param message Message to log
     */
    void error(String message);
}
