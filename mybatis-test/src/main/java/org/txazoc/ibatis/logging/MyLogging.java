package org.txazoc.ibatis.logging;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.logging.Log;

import java.util.Date;

public class MyLogging implements Log {

    private static final String LOGGING_NAME = "MYLOGGING";
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    private String clazz;

    public MyLogging(String clazz) {
        this.clazz = clazz;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public boolean isTraceEnabled() {
        return false;
    }

    public void error(String s, Throwable e) {
        System.err.println(wrapLog("error", s));
        e.printStackTrace(System.err);
    }

    public void error(String s) {
        System.err.println(wrapLog("error", s));
    }

    public void debug(String s) {
        System.out.println(wrapLog("debug", s));
    }

    public void trace(String s) {
        System.out.println(wrapLog("trace", s));
    }

    public void warn(String s) {
        System.out.println(wrapLog("warn", s));
    }

    private String wrapLog(String level, String s) {
        return DateFormatUtils.format(new Date(), FORMAT) + " [" + clazz + "] " + LOGGING_NAME + " " + level.toUpperCase() + " - " + s;
    }

}
