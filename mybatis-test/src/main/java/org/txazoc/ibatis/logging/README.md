MyBatis日志
==============

MyBatis没有实现自己的日志组件，而是封装已有的日志组件，包括Slf4j、Commons Logging、Log4J2、Log4J、Jdk Logging，在LogFactory初始化的时候，也是按照这个顺序来查找并初始化的。

知道MyBatis的日志实现原理，事情就变得简单了。如果要在MyBatis中使用某一种日志框架，引入框架的jar包，然后添加配置文件就可以了。如果同时使用了多种日志配置，会按照Slf4j、Commons Logging、Log4J2、Log4J的优先级来决定使用哪种日志组件。如果没有配置任何日志组件，默认会使用Jdk Logging。

如果想在MyBatis中使用自定义的日志组件，MyBatis也提供了支持。首先，自定义的日志组件要实现org.apache.ibatis.logging.Log，并提供String参数的构造方法，然后，在mybatis.xml文件的<settings>中配置logImpl就可以了。

```java
public class MyLogging implements Log {

    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss:SSS";

    private String clazz;

    public MyLogging(String clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(wrapLog("error", s));
        e.printStackTrace(System.err);
    }

    @Override
    public void error(String s) {
        System.err.println(wrapLog("error", s));
    }

    @Override
    public void debug(String s) {
        System.out.println(wrapLog("debug", s));
    }

    @Override
    public void trace(String s) {
        System.out.println(wrapLog("trace", s));
    }

    @Override
    public void warn(String s) {
        System.out.println(wrapLog("warn", s));
    }

    private String wrapLog(String level, String s) {
        return DateFormatUtils.format(new Date(), FORMAT) + " " + clazz + " " + level.toUpperCase() + " " + s;
    }

}
```

```xml
<settings>
    <setting name="logImpl" value="org.txazoc.ibatis.logging.MyLogging" />
</settings>
```

这种配置方式也可以实现指定加载某种日志组件。

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

```java
private static final Log log = LogFactory.getLog(TransactionalCache.class);
```
