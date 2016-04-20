MyBatis日志
==============

MyBatis没有实现自己的日志组件，而是提供对其它日志组件的支持，括Slf4j、Commons Logging、Log4J2、Log4J、Jdk Logging。

下面先来谈下MyBatis日志的实现原理。首先，MyBatis启动的时候会加载执行LogFactory的static代码块。在static代码块中，MyBatis按照Slf4j、Commons Logging、Log4J2、Log4J、Jdk Logging的顺序依次尝试加载日志组件，加载成功则使用相应的日志组件，否则尝试加载下一个日志组件。在没有引入任何日志组件的情况下，MyBatis默认会使用Jdk Logging。

下面是MyBatis的static代码块的源码。

```java
static {
    tryImplementation(new Runnable() {
        @Override
        public void run() {
            useSlf4jLogging();
        }
    });

    tryImplementation(new Runnable() {
        @Override
        public void run() {
            useCommonsLogging();
        }
    });

    tryImplementation(new Runnable() {
        @Override
        public void run() {
            useLog4J2Logging();
        }
    });

    tryImplementation(new Runnable() {
        @Override
        public void run() {
            useLog4JLogging();
        }
    });

    tryImplementation(new Runnable() {
        @Override
        public void run() {
            useJdkLogging();
        }
    });

    tryImplementation(new Runnable() {
        @Override
        public void run() {
            useNoLogging();
        }
    });
}
```

清楚MyBatis的日志实现原理后，事情就变得简单了。如果要在MyBatis中使用某一种日志组件，引入相应的jar包，然后添加配置文件就可以了。如果同时使用了多种日志组件，会按照Slf4j、Commons Logging、Log4J2、Log4J的优先级来进行加载。如果没有引入任何日志组件，默认会使用Jdk Logging。

上面是MyBatis提供的自动日志加载功能，MyBatis还提供了配置的方式指定日志组件。在MyBatis的XMLConfigBuilder的settingsElement()方法中有下面这段代码，这段代码实现的功能是指定新的日志组件。

```java
configuration.setLogImpl(resolveClass(props.getProperty("logImpl")));
```

这样，我们就可以在配置文件中直接指定使用哪一种日志组件了。

```xml
<settings>
    <setting name="logImpl" value="SLF4J" />
    <setting name="logImpl" value="COMMONS_LOGGING" />
    <setting name="logImpl" value="LOG4J" />
    <setting name="logImpl" value="LOG4J2" />
    <setting name="logImpl" value="LOG4J" />
    <setting name="logImpl" value="JDK_LOGGING" />
    <setting name="logImpl" value="STDOUT_LOGGING" />
    <setting name="logImpl" value="NO_LOGGING" />
</settings>
```

如果想在MyBatis中使用自定义的日志组件，MyBatis也提供了支持。首先，自定义的日志组件要实现org.apache.ibatis.logging.Log接口，并提供String参数的构造方法，然后，配置logImpl就ok了。

```java
public class MyLogging implements Log {

    private static final String LOGGING_NAME = "MYLOGGING";
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    private String clazz;

    public MyLogging(String clazz) {
        this.clazz = clazz;
    }

    public boolean isDebugEnabled() {
        return false;
    }

    public boolean isTraceEnabled() {
        return false;
    }

    public void error(String s, Throwable e) {
        System.err.println(wrapLog("error", s));
        e.printStackTrace(System.err);
    }

    public void error(String s) {
        System.err.println(wrapLog("error", s));
    }

    public void debug(String s) {
        System.out.println(wrapLog("debug", s));
    }

    public void trace(String s) {
        System.out.println(wrapLog("trace", s));
    }

    public void warn(String s) {
        System.out.println(wrapLog("warn", s));
    }

    private String wrapLog(String level, String s) {
        return DateFormatUtils.format(new Date(), FORMAT) + " [" + clazz + "] " + LOGGING_NAME + " " + level.toUpperCase() + " - " + s;
    }

}
```

```xml
<settings>
    <setting name="logImpl" value="org.txazoc.ibatis.logging.MyLogging" />
</settings>
```

最后就是如何使用MyBatis日志了，直接调用LogFactory.get()拿到Log，使用Log就可以下日志了。

```java
private static final Log log = LogFactory.getLog(LogTest.class);
```
