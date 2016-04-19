package org.txazoc.ibatis;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class MyBatisTest {

    private static SqlSession session;

    @BeforeClass
    public static void beforeClass() throws IOException {
        String resource = "mybatis.xml";
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(resource));
        session = factory.openSession();
    }

    @AfterClass
    public static void afterClass() {
        session.commit();
        session.close();
    }

    @Test
    public void testMyBatisStart() {
    }

}
