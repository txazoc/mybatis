package org.txazoc.ibatis.test;

public class MyBatisJUnitException extends RuntimeException {

    public MyBatisJUnitException() {
        super();
    }

    public MyBatisJUnitException(String message) {
        super(message);
    }

    public MyBatisJUnitException(Throwable cause) {
        super(cause);
    }

    public MyBatisJUnitException(String message, Throwable cause) {
        super(message, cause);
    }

}
