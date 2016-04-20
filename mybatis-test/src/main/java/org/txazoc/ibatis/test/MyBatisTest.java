package org.txazoc.ibatis.test;

import org.apache.ibatis.session.SqlSession;
import org.junit.Assert;

public class MyBatisTest extends Assert {

    protected SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

}
