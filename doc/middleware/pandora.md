类加载隔离框架, 系统启动时，加载多个模块时，容易出现命名一样的类，从而导致noSuchMethodError等报错.

例如：
在系统工程中依赖了A和B jar包， A、B jar又都依赖了C的LogXXX类，A依赖V1.0，B依赖V1.5，在系统代码里使用到了V1.5 LogXXX#foo方法，它在V1.0版本是
不存在的。 此时如果系统根据maven最短路径或者优先声明原则，引入了V1.0版本的C，编译时虽然不会报错，运行时则会导致noSuchMethodError.

如何实现类加载隔离？
使用不同的类加载器加载各自模块的类。

JVM Class = 全类限定名 + 类加载器， JVM使用字典存储

双亲委派类加载
1、检查类有没有被加载
2、如果没有被加载，则调用父类加载器加载
3、1、2都不成功，则人有自身进行类加载
CustomClassLoader: java.lang.ClassLoader的子类自定义加载
-> 
AppClassLoader ：负责加载classpath环境变量里面指定目录、用户编写及引用的第三方库下的类。
-> 
ExtensionClassLoader：负责加载$JAVA_HOME/lib/ext 目录或者java.ext.dirs系统变量指定目录下的类，主要是一些扩展类。
->
BootstrapClassLoader: 负责加载$JAVA_HOME/jre/lib目录下的类，这里都是一些JDK内部比较核心的类，比如java.lang.* ，java.util.*等等，它和其
它类加载器不同的是它是由native实现，特别的是另外两个类加载器也是由它加载的。

好处
1、保证核心类的安全
2、避免类被反复加载





