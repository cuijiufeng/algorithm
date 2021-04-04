package cn.resource.code;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.MethodVisitor;
import jdk.internal.org.objectweb.asm.Opcodes;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/**
 * @ClassName: ByteCodeTest
 * @Date: 2020/10/27 8:44
 * @auth: Administrator
 * 字节码技术对方法增强
 * 可以看出已经可以实现方法的增强，下面是代理类的代码
 * package leetcode.editor.cn;
 * import java.lang.reflect.Method;
 * public class HelloTest$Proxy extends HelloTest {
 *      private InvocationHandler handler;
 *      public HelloTest$Proxy(InvocationHandler var1) {
 *           this.handler = var1;
 *      }
 *      public void sayHello() {
 *           this.handler.invoke((Object)null, (Method)null, (Object[])null);
 *      }
 * }
 */
public class ByteCodeTest {
    public static void main(String[] args) throws Exception {
        //调用方法生成代理对象
        HelloTestTest proxyObj = (HelloTestTest) Proxy.newProxyInstance(HelloTestTest.class, new InvocationHandler() {
            //实现回调方法
            @Override
            public void invoke(Object proxy, Method method, Object[] args) throws Throwable {
                //方法增强逻辑
                System.out.println("hahahah");
                return;
            }
        });
        proxyObj.sayHello();
    }
}

/*
 * 回调接口
 * */
interface InvocationHandler {
    void invoke(Object proxy, Method method, Object[] args) throws Throwable;
}

/*
 * 代理生成类
 * */
class Proxy{
    public static Object newProxyInstance(Class<?> clazz, InvocationHandler handler) throws Exception {
        //被代理类名
        String clazzName = clazz.getName().replace(".", "/");
        //被代理类方法
        Method[] methods = clazz.getMethods();

        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //代理类继承被代理类
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, clazzName + "$Proxy", null, clazzName, null);
        //代理对象添加回调接口属性
        cw.visitField(Opcodes.ACC_PRIVATE, "handler", "L"+InvocationHandler.class.getName().replace('.', '/')+";", InvocationHandler.class.getName().replace('.', '/'), null);
        //代理对象生成构造方法
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC, "<init>", "(L"+InvocationHandler.class.getName().replace('.', '/')+";)V", null, null);
        //设置操作数栈最大深度与局部变量表最大数量
        mv.visitMaxs(2,2);
        //调用父类构造方法
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, clazzName, "<init>", "()V", false);
        //回调接口赋值
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        mv.visitVarInsn(Opcodes.ALOAD, 1);
        mv.visitFieldInsn(Opcodes.PUTFIELD, clazzName + "$Proxy", "handler", "L"+InvocationHandler.class.getName().replace('.', '/')+";");
        mv.visitInsn(Opcodes.RETURN);
        //方法完毕
        mv.visitEnd();
        //循环增强所有被代理类方法
        for (int i = 0; i < methods.length; i++) {
            String methodName = methods[i].getName();
            //排除object类方法
            if (methodName.equals("wait") || methodName.equals("notify") || methodName.equals("notifyAll") || methodName.equals("equals") || methodName.equals("hashCode")
                    || methodName.equals("getClass") || methodName.equals("clone") || methodName.equals("finalize") || methodName.equals("toString")){
                continue;
            }
            //构造被代理类方法的描述符参数部分
            String desc = "";
            Class<?>[] parameterTypes = methods[i].getParameterTypes();
            desc += "(";
            for (int j = 0; j < parameterTypes.length; j++) {
                String name = parameterTypes[j].getName();
                if ("int".equals(parameterTypes[j].getName())){
                    desc += "I";
                    if (j != parameterTypes.length - 1){
                        desc += ",";
                    }
                } else if ("long".equals(parameterTypes[j].getName())){
                    desc += "J";
                    if (j != parameterTypes.length - 1){
                        desc += ",";
                    }
                } else {
                    desc += "L" + parameterTypes[j].getName().replace('.', '/') + ";";
                    if (j != parameterTypes.length - 1){
                        desc += ",";
                    }
                }
            }
            desc += ")";
            //构造被代理类方法的描述符返回值部分
            Class<?> returnType = methods[i].getReturnType();
            if ("void".equals(returnType.getName())){
                desc += "V";
            } else if ("boolean".equals(returnType.getName())){
                desc += "Z";
            } else if ("int".equals(returnType.getName())){
                desc += "I";
            } else {
                desc += "L" + returnType.getName().replace('.', '/') + ";";
            }
            //方法生成
            mv = cw.visitMethod(Opcodes.ACC_PUBLIC, methods[i].getName(), desc, null, null);
            //设置操作数栈最大深度与局部变量表最大数量
            mv.visitMaxs(4,4);
            //获得回调接口
            mv.visitVarInsn(Opcodes.ALOAD, 0);
            mv.visitFieldInsn(Opcodes.GETFIELD, clazzName + "$Proxy", "handler", "L"+InvocationHandler.class.getName().replace('.', '/')+";");

            //下面是传的三个参数，暂时没有想到如果把constructor.newInstance(handler)、methods[i]和parameterTypes传过去
            //mv.visitTypeInsn(Opcodes.NEW, "java/lang/Object");
            //mv.visitInsn(Opcodes.DUP);
            //mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(Opcodes.ACONST_NULL);

            //mv.visitTypeInsn(Opcodes.NEW, "java/lang/Object");
            //mv.visitInsn(Opcodes.DUP);
            //mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
            mv.visitInsn(Opcodes.ACONST_NULL);

            //mv.visitInsn(Opcodes.ICONST_1);
            //mv.visitTypeInsn(Opcodes.ANEWARRAY, "java/lang/Object");
            mv.visitInsn(Opcodes.ACONST_NULL);

            //调用回调接口方法
            mv.visitMethodInsn(
                    Opcodes.INVOKEINTERFACE,
                    InvocationHandler.class.getName().replace('.', '/'),
                    InvocationHandler.class.getMethod("invoke", Object.class, Method.class, Object[].class).getName(),
                    "(Ljava/lang/Object;Ljava/lang/reflect/Method;[Ljava/lang/Object;)V",
                    true);
            //方法返回
            mv.visitInsn(Opcodes.RETURN);
            //方法构造完毕
            mv.visitEnd();
        }
        //类构造完毕
        cw.visitEnd();

        //字节码生成,可在此时用流输出byteCode到文件后用javap -v file查看字节码对应的类模样
        byte[] byteCode = cw.toByteArray();
        //try (DataOutputStream dos = new DataOutputStream(new FileOutputStream("C:\\Users\\cui\\Desktop\\file.class"))){
        //    dos.write(byteCode);
        //}
        //获得被代理类的ClassLoader类加载器，里边有defineClass方法，可加载类字节码
        Class<?> aClass = clazz.getClassLoader().getClass();
        while (!"java.lang.ClassLoader".equals(aClass.getName())){
            aClass = aClass.getSuperclass();
        }
        //获得ClassLoader类中的defineClass方法
        Method method = aClass.getDeclaredMethod("defineClass", byte[].class, int.class, int.class);
        //设置方法可见
        method.setAccessible(true);
        //反射调用ClassLoader类中的defineClass方法生成代理类的字节码Class对象
        Class<?> bClass = (Class<?>) method.invoke(clazz.getClassLoader(), byteCode, 0, byteCode.length);
        //获得代理类的构造方法
        Constructor<?> constructor = bClass.getConstructor(InvocationHandler.class);
        //生成代理类的对象
        return constructor.newInstance(handler);
    }
}

/**
 * 待被增强类
 */
class HelloTestTest {
    public void sayHello(){
        System.out.println("hello");
    }
}