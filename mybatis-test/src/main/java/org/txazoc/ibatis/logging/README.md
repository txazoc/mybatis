MyBatis日志
==============

MyBatis没有实现自己的日志组件，而是封装已有的日志组件，包括Slf4j、Commons Logging、Log4J2、Log4J、Jdk Logging，在LogFactory初始化的时候，也是按照这个顺序来查找并初始化的。

知道MyBatis的日志实现原理，事情就变得简单了。如果要在MyBatis中使用某一种日志框架，引入框架的jar包，然后添加配置文件就可以了。如果同时使用了多种日志配置，会按照Slf4j、Commons Logging、Log4J2、Log4J的优先级来决定使用哪种日志组件。如果没有配置任何日志组件，默认会使用Jdk Logging。

如果想在MyBatis中使用自定义的日志组件，MyBatis也提供了支持，在mybatis.xml文件的<settings>中配置logImpl就可以了。

```xml
<settings>
    <setting name="logImpl" value="LOG4J" />
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
