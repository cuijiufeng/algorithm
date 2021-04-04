package cn.resource.code;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName: AopTest
 * @Date: 2020/10/27 11:56
 * @auth: Administrator
 */
public class AopTest {
    public static void main(String[] args){
        Hello helloObj = new HelloTest();
        //生成代理对象
        Hello proxyObj = (Hello) Proxy.newProxyInstance(
                //类加载器，使用被代理对象类的类加载器即可
                HelloTestTest.class.getClassLoader(),
                //被代理对象实现的接口
                new Class[]{Hello.class},
                //InvocationHandler
                new InvocationHandler() {
                    //1。代理对象    2。调用的方法 3。方法调用的参数
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        //增强sayHello方法
                        if ("sayHello".equals(method.getName())){
                            System.out.printf("every one ");
                        }
                        //其余方法调用原始对象的方法
                        return method.invoke(helloObj, args);
                    }
                });
        System.out.printf("no proxy obj:\t");
        helloObj.sayHello();
        System.out.printf("proxy obj:\t");
        proxyObj.sayHello();
    }
}

interface Hello{
    void sayHello();
}

class HelloTest implements Hello{
    @Override
    public void sayHello(){
        System.out.println("hello");
    }
}
