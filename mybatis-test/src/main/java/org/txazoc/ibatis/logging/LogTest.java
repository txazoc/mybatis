package org.txazoc.ibatis.logging;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.log4j.Log4jImpl;
import org.apache.ibatis.logging.log4j2.Log4j2Impl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.txazoc.ibatis.test.MyBatisJUnitRunner;
import org.txazoc.ibatis.test.MyBatis;
import org.txazoc.ibatis.test.MyBatisTest;

@RunWith(MyBatisJUnitRunner.class)
public class LogTest extends MyBatisTest {

    @Test
    @MyBatis(resource = "org/txazoc/ibatis/logging/mybatis-default.xml", openSession = false)
    public void testDefault() {
        Log log = LogFactory.getLog(LogTest.class);
        assertEquals(log.getClass(), Log4j2Impl.class);
        log.debug("test");
    }

    @Test
    @MyBatis(resource = "org/txazoc/ibatis/logging/mybatis-log4j.xml", openSession = false)
    public void testLog4j() {
        Log log = LogFactory.getLog(LogTest.class);
        assertEquals(log.getClass(), Log4jImpl.class);
        log.debug("test");
    }

    @Test
    @MyBatis(resource = "org/txazoc/ibatis/logging/mybatis-mylogging.xml", openSession = false)
    public void testMyLogging() {
        Log log = LogFactory.getLog(LogTest.class);
        assertEquals(log.getClass(), MyLogging.class);
        log.debug("test");
    }

}
