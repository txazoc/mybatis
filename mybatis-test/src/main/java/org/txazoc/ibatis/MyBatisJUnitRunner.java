package org.txazoc.ibatis;

import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.JUnit4;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.Statement;

public class MyBatisJUnitRunner extends BlockJUnit4ClassRunner {

    public MyBatisJUnitRunner(Class<?> clazz) throws InitializationError {
        super(clazz);
    }

    @Override
    protected Statement withBeforeClasses(Statement statement) {
        return super.withBeforeClasses(statement);
    }

    @Override
    protected Statement methodBlock(FrameworkMethod method) {
        return super.methodBlock(method);
    }

}
