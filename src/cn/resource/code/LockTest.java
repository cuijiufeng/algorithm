package cn.resource.code;

import java.util.concurrent.TimeUnit;
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
 *
 * 解读-加锁过程
 * 1.public ReentrantLock() { sync = new NonfairSync(); } 默认构造函数为非公平锁，可通过fair构造函数来构造公平锁
 * 2.private final Sync sync;具体是通过此对象来控制的原子性
 *  AbstractOwnableSynchronizer -> AbstractQueuedSynchronizer -> Sync,Sync又派生出FairSync与NonfairSync
 * 3.NonfairSync中
 *  final void lock() {
 *      //AbstractQueuedSynchronizer有一个state属性(初始值为0),当if判断成功获取锁成功。并记录取得锁的线程
 *      //AbstractQueuedSynchronizer还有prev与head两个Node类型的属性，它是一个队列。这是一个等待获取锁的阻塞队列。此队列会存在一个空结节作为头结点
 *      if (compareAndSetState(0, 1))
 *          //记录获取到锁的线程
 *          setExclusiveOwnerThread(Thread.currentThread());
 *      //如果获取锁失败，
 *      else
 *          acquire(1);
 *  }
 *  //尝试获取锁
 *  protected final boolean tryAcquire(int acquires) {
 *      return nonfairTryAcquire(acquires);
 *  }
 *  final boolean nonfairTryAcquire(int acquires) {
 *      //尝试获取锁的线程
 *      final Thread current = Thread.currentThread();
 *      //获取锁的状态
 *      int c = getState();
 *      //如果锁此时是可获取状态(其它线程已将锁释放)
 *      if (c == 0) {
 *          //再次尝试去获取锁，将状态由0改为1
 *          if (compareAndSetState(0, acquires)) {
 *              //记录获取到锁的线程
 *              setExclusiveOwnerThread(current);
 *              return true;
 *          }
 *      }
 *      //如果是锁重入状态
 *      else if (current == getExclusiveOwnerThread()) {
 *          int nextc = c + acquires;
 *          if (nextc < 0) // overflow
 *              throw new Error("Maximum lock count exceeded");
 *          //因为此时是已经锁住的状态，所以修改state值是线程安全的。锁重入重新获取锁时state值再加1
 *          setState(nextc);
 *          return true;
 *      }
 *      return false;
 *  }
 * 4.AbstractQueuedSynchronizer中
 *  public final void acquire(int arg) {
 *      //再次尝试获取锁，如果获取成功无动作，如果获取失败则中断获取锁的线程
 *      //addWaiter(Node.EXCLUSIVE)会将准备获取锁的线程放入队列尾部,并返回此线程队列Node结点
 *      if (!tryAcquire(arg) && acquireQueued(addWaiter(Node.EXCLUSIVE), arg))
 *          //则中断准备获取锁的线程(thread.interrupt是发送一个中断信号，并不会立即中断)。
 *          selfInterrupt();
 *  }
 *  final boolean acquireQueued(final Node node, int arg) {
 *      boolean failed = true;
 *      try {
 *          boolean interrupted = false;
 *          for (;;) {
 *              //取等待队列当前结点的前一结点
 *              final Node p = node.predecessor();
 *              //如果是这队列的唯一一个线程结点则再次尝试获取锁(循环在这里尝试获取)
 *              if (p == head && tryAcquire(arg)) {
 *                  setHead(node);
 *                  p.next = null; // help GC
 *                  failed = false;
 *                  return interrupted;
 *              }
 *              //这里就涉及到对象头MarkWord了。由轻量级锁转为重量级锁也是从这里转变的
 *              if (shouldParkAfterFailedAcquire(p, node) && parkAndCheckInterrupt())
 *                  interrupted = true;
 *          }
 *      } finally {
 *          if (failed)
 *              cancelAcquire(node);
 *      }
 *  }
 *  private final boolean parkAndCheckInterrupt() {
 *      LockSupport.park(this);
 *      //判断当前线程是否是被中断了
 *      return Thread.interrupted();
 *  }
 * 5.LockSupport
 *  public static void park(Object blocker) {
 *      Thread t = Thread.currentThread();
 *      setBlocker(t, blocker);
 *      //挂起当前线程，false表示相对时间(true方绝对时间),0L时间表示永久挂起，直至被唤醒。park与unpark时linux底层_counter变量也会随之改变
 *      UNSAFE.park(false, 0L);
 *      setBlocker(t, null);
 *  }
 *
 * 解读-解锁过程
 * 1.AbstractQueuedSynchronizer中
 *  public final boolean release(int arg) {
 *      //尝试释放锁
 *      if (tryRelease(arg)) {
 *          Node h = head;
 *          //如果当前锁是一个重量级锁，则
 *          if (h != null && h.waitStatus != 0)
 *              //释放锁
 *              unparkSuccessor(h);
 *          return true;
 *      }
 *      return false;
 *  }
 *  private void unparkSuccessor(Node node) {
 *      //最先传过来的是等待队列的头结点，等待状态
 *      int ws = node.waitStatus;
 *      if (ws < 0)
 *          compareAndSetWaitStatus(node, ws, 0);
 *      Node s = node.next;
 *      if (s == null || s.waitStatus > 0) {
 *          s = null;
 *          for (Node t = tail; t != null && t != node; t = t.prev)
 *              if (t.waitStatus <= 0)
 *                  s = t;
 *      }
 *      //unpark
 *      if (s != null)
 *          LockSupport.unpark(s.thread);
 *  }
 * 2.ReentrantLock中
 *  protected final boolean tryRelease(int releases) {
 *      //判断当前锁的状态
 *      int c = getState() - releases;
 *      //如果当前准备释放锁的线程不是获取到锁的线程则抛异常
 *      if (Thread.currentThread() != getExclusiveOwnerThread())
 *          throw new IllegalMonitorStateException();
 *      boolean free = false;
 *      //如果是当前线程锁住了，并且释放完之后锁的状态正好是0(锁为可重入锁，重入获得锁的状态不是一定是1)。则释放锁，并设置当前获取锁的线程为null
 *      if (c == 0) {
 *          free = true;
 *          setExclusiveOwnerThread(null);
 *      }
 *      //释放锁
 *      setState(c);
 *      return free;
 *  }
 */
public class LockTest {
    public static void main(String[] args) throws InterruptedException {
        //锁:实现-可重入锁
        Lock lock = new ReentrantLock();
        //获取锁()
        lock.lock();
        System.out.println("main获取锁");
        //获取锁(重入获取锁时释放一次锁，锁状态state只会减一)
        //lock.lock();
        try {
            Thread thread = new Thread(() -> {
                lock.lock();
                System.out.println("thread获取锁");
                try {
                    TimeUnit.SECONDS.sleep(20);
                } catch (InterruptedException e) {
                }
                System.out.println("thread释放锁");
                lock.unlock();
            });
            thread.start();
        } finally {
            TimeUnit.SECONDS.sleep(10);
            System.out.println("main释放锁");
            //在出现异常的时候不会自动释放锁，所以要在finnaly里把锁释放掉
            lock.unlock();
        }
    }
}
