# Java基础
[Java8 JDK文档](http://www.matools.com/api/java8)

>Java为了提高效率引入了基本数据类型，如 int char byte double float long

>Java的内存管理机制请百度:)

>由于基本数据类型不属于对象，所以当你想把int类型转变为String类型时，无法使用toString方法，只能将变量定义为Integer类型。

>Java自动拆装箱机制 基本类型与引用类型可以自动转换，如以下代码中，test方法入参为int类型，但在使用中传入Integer类型也没有问题。

~~~Java
public class Demo {
    public static void main(String[] args) {
        Integer a = 1;
        test(a);
    }

    private static int test(int a){
        Integer b = a;
        return  b;
    }
}
~~~
>Java变量定义 [访问修饰符](https://blog.csdn.net/qq_44013790/article/details/84984353) + 数据类型 + 变量名

>[static修饰符](https://baijiahao.baidu.com/s?id=1601254463089390982&wfr=spider&for=pc)

>[final修饰符](https://baijiahao.baidu.com/s?id=1601084106055683243&wfr=spider&for=pc)

>Java方法定义 访问修饰符 + 返回值 + 方法名(入参){方法体}

