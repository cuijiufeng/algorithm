package cn.resource.code;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * java轻量级锁原理：通过循环的方式去试探，如果在操作的时候有其他的线程改变了变量的值，则说明需要重新去读取变量的值。
 * //volatile多线程可见性
 * private volatile int value;
 * //自增
 * public final int incrementAndGet() {
 *         return unsafe.getAndAddInt(this, valueOffset, 1) + 1;
 *     }
 * public final int getAndAddInt(Object obj, long off, int num) {
 *         int i;
 *         do {
 *              //获取当前AtomicInteger的value属性
 *             i = this.getIntVolatile(obj, off);
 *             //取obj对象的value属性值与i值比较，如果同等则把i+num赋值给i。否则取消赋值继续循环
 *         } while(!this.compareAndSwapInt(obj, off, i, i + num));
 *         return i;
 *     }
 * @ClassName: AtomicTest
 * @Date: 2020/9/18 14:52
 * @auth: Administrator
 */
public class AtomicTest {
    //private Integer number = 0;
    private AtomicInteger number = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        AtomicTest test = new AtomicTest();
        test.f();
    }

    private void f() throws Exception {
        Thread t1 = new Thread(new MyIncreThread());
        t1.start();
        Thread t2 = new Thread(new MyDecreThread());
        t2.start();
        //等待t1\t2执行完毕
        t1.join();
        t2.join();
        System.out.println(number);
    }

    public class MyIncreThread implements Runnable{

        @Override
        public void run() {
            for (int i=0; i<10000; i++) {
                number.incrementAndGet();
            }
        }
    }

    public class MyDecreThread implements Runnable{

        @Override
        public void run() {
            for (int i=0; i<10000; i++) {
                number.decrementAndGet();
            }
        }
    }
}
