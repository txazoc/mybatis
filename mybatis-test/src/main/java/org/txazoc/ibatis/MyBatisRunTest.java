package org.txazoc.ibatis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.txazoc.ibatis.test.MyBatis;
import org.txazoc.ibatis.test.MyBatisJUnitRunner;
import org.txazoc.ibatis.test.MyBatisTest;

@RunWith(MyBatisJUnitRunner.class)
public class MyBatisRunTest extends MyBatisTest {

    @Test
    @MyBatis(resource = "mybatis.xml")
    public void testRun() {

    }

}
