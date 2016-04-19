package org.txazoc.ibatis.logging;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.ibatis.logging.Log;

import java.util.Date;

public class MyLogging implements Log {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    private String clazz;

    public MyLogging(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(wrapLog("error", s));
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        System.err.println(wrapLog("error", s));
    }

    @Override
    public void debug(String s) {
        System.out.println(wrapLog("debug", s));
    }

    @Override
    public void trace(String s) {
        System.out.println(wrapLog("trace", s));
    }

    @Override
    public void warn(String s) {
        System.out.println(wrapLog("warn", s));
    }

    private String wrapLog(String level, String s) {
        return DateFormatUtils.format(new Date(), FORMAT) + " " + clazz + " " + level.toUpperCase() + " " + s;
    }

}
