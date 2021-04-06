package cn.resource.code;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * @date 2021/4/6 13:32
 * @auth cuijiuifeng
 * @desc
 * synchronized与lock的区别
 * 1.synchronized和lock都是可重入锁
 * 2.synchronized异常时会自动释放锁，lock在异常时不会自动释放锁
 * 3.synchronized是不可中断锁，lock是可中断锁。(可中断锁:在等待获取锁的时候取消等待)
 * 4.synchronized是非公平锁，lock默认是非公平锁可以修改设置为公平锁。(公平锁:获取锁的线程是按顺序获得。非公平锁可能会导致一个线程永远都获取不到锁)
 * 5.synchrronized和reentrantlock是独占锁，ReadWriteLock是共享锁。(独占锁与共享锁:独占锁只能被一个线程所获得，共享锁可以被几个线程共享)
 */
public class LockTest {
    //锁:实现-可重入锁
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        try {
            //获取锁
            lock.lock();
            //尝试获取锁，获取成功返回true。被其他线程获取则返回失败
            System.out.println(lock.tryLock());
        } catch (Throwable e) {
            //在出现异常的时候不会自动释放锁，所以要在finnaly里把锁释放掉
        } finally {
            //释放锁
            lock.unlock();
        }
    }
}
