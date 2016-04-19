package org.txazoc.ibatis.logging;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.txazoc.ibatis.MyBatisJUnitRunner;
import org.txazoc.ibatis.MyBatisTest;

@RunWith(MyBatisJUnitRunner.class)
public class MyLoggingTest {

    @BeforeClass
    public static void before() {
        Assert.assertTrue(true);
    }

    @Test
    public void test() {
        Assert.assertTrue(true);
    }

}
