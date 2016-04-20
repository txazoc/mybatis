package org.txazoc.ibatis.test;

import org.apache.ibatis.cache.decorators.SynchronizedCache;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunListener;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;
import org.junit.runners.model.TestClass;
import org.txazoc.ibatis.test.MyBatis;
import org.txazoc.ibatis.test.MyBatisJUnitException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.rmi.server.ExportException;

public class MyBatisJUnitRunner extends BlockJUnit4ClassRunner {

    private Class<?> testClass;
    private boolean isMyBatisTest = false;
    private Method setSqlSessionMethod;

    public MyBatisJUnitRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
        initMyBatisTest(clazz);
    }

    private void initMyBatisTest(Class<?> testClass) {
        this.testClass = testClass;
        this.isMyBatisTest = MyBatisTest.class.isAssignableFrom(testClass);
        if (isMyBatisTest) {
            try {
                setSqlSessionMethod = testClass.getMethod("setSqlSession", SqlSession.class);
            } catch (Exception e) {
                throw new MyBatisJUnitException("MyBatis init failed");
            }
        }
    }

    @Override
    protected Statement methodInvoker(FrameworkMethod method, Object test) {
        injectSqlSession(method, test);
        return super.methodInvoker(method, test);
    }

    private void injectSqlSession(FrameworkMethod method, Object test) {
        if (this.isMyBatisTest) {
            MyBatis myBatis = method.getAnnotation(MyBatis.class);
            if (myBatis != null) {
                try {
                    SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream(myBatis.resource()));
                    if (myBatis.openSession()) {
                        setSqlSessionMethod.invoke(test, factory.openSession());
                    }
                } catch (Exception e) {
                    throw new MyBatisJUnitException("MyBatis sqlSession inject failed");
                }
            }
        }
    }

}
