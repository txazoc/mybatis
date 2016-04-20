package org.txazoc.ibatis.test;

import org.apache.ibatis.session.SqlSession;

public class MyBatisTest {

    protected SqlSession sqlSession;

    public void setSqlSession(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

}
