package cn.resource.code;

import java.util.concurrent.*;

/**
 * @ClassName: 线程池
 * @Date: 2020/10/9 10:24
 * @auth: Administrator
 * //问题：
 * 1。在任务使用完线程之后是如何放回池中的
 * 2。阻塞队列中的任务是怎样被执行起来的
 *
 * //记录了线程池的状态与线程数量,使用轻量级锁保证线程安全的
 * AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
 * //线程任务集合
 * private final HashSet<Worker> workers = new HashSet<Worker>();
 * //阻塞队列
 * private final BlockingQueue<Runnable> workQueue;
 *
 * //执行一个线程
 * public void execute(Runnable command) {
 *      //如果线程为空则抛异常
 *     if (command == null)
 *         throw new NullPointerException();
 *     //取线程的状态与数量
 *     int c = ctl.get();
 *     //如果线程的数量小于核心线程，则设置为核心线程
 *     if (workerCountOf(c) < corePoolSize) {
 *          //添加线程到线程池中
 *         if (addWorker(command, true))
 *             return;
 *         c = ctl.get();
 *     }
 *     //如果线程池运行状态并且入阻塞队列成功
 *     if (isRunning(c) && workQueue.offer(command)) {
 *         int recheck = ctl.get();
 *         //线程池不为运行状态且删除阻塞队列中添加的线程成功
 *         if (! isRunning(recheck) && remove(command))
 *             reject(command);
 *         //如果线程池中线程数量为0则添加为非核心线程
 *         else if (workerCountOf(recheck) == 0)
 *             addWorker(null, false);
 *     }
 *     //添加为非核心线程
 *     else if (!addWorker(command, false))
 *         reject(command);
 * }
 * //往线程池中添加线程
 * private boolean addWorker(Runnable firstTask, boolean core) {
 *     retry:
 *     for (;;) {
 *         int c = ctl.get();
 *         //获得线程池的状态
 *         int rs = runStateOf(c);
 *         //如果以下情况，添加线程失败
 *         if (rs >= SHUTDOWN && !(rs == SHUTDOWN && firstTask == null && !workQueue.isEmpty()))
 *             return false;
 *         for (;;) {
 *              //获得线程的数量
 *             int wc = workerCountOf(c);
 *             //失败
 *             if (wc >= CAPACITY || wc >= (core ? corePoolSize : maximumPoolSize))
 *                 return false;
 *             //线程数量加一,如果成功则跳出retry其后的循环
 *             if (compareAndIncrementWorkerCount(c))
 *                 break retry;
 *             //重新获取线程的状态
 *             c = ctl.get();
 *             //如果线程池的状态改变则重试
 *             if (runStateOf(c) != rs)
 *                 continue retry;
 *         }
 *     }
 *     boolean workerStarted = false;
 *     boolean workerAdded = false;
 *     Worker w = null;
 *     try {
 *          //new一个线程，Worker实现了Runnable
 *         w = new Worker(firstTask);
 *         //获取新new的线程
 *         final Thread t = w.thread;
 *         if (t != null) {
 *              //锁重入锁
 *             final ReentrantLock mainLock = this.mainLock;
 *             //加锁
 *             mainLock.lock();
 *             try {
 *                  //获取线程池状态
 *                 int rs = runStateOf(ctl.get());
 *                 //如果线程池状态为运行状态或者线程池为关闭状态并且线程为null
 *                 if (rs < SHUTDOWN || (rs == SHUTDOWN && firstTask == null)) {
 *                      //如果线程是活着的则异常
 *                     if (t.isAlive()) // precheck that t is startable
 *                         throw new IllegalThreadStateException();
 *                     //将线程任务添加到hashset
 *                     workers.add(w);
 *                     //获得hashset大小
 *                     int s = workers.size();
 *                     //如果hasahset大小大于线程池当前最大池大小
 *                     if (s > largestPoolSize)
 *                         largestPoolSize = s;
 *                     //标志任务添加
 *                     workerAdded = true;
 *                 }
 *             } finally {
 *                  //解锁
 *                 mainLock.unlock();
 *             }
 *             //如果任务已经添加则启动
 *             if (workerAdded) {
 *                 t.start();
 *                 workerStarted = true;
 *             }
 *         }
 *     } finally {
 *          //如果任务启动失败
 *         if (! workerStarted)
 *             addWorkerFailed(w);
 *     }
 *     return workerStarted;
 * }
 */
public class 线程池 {
    public static void main(String[] args) throws Exception{
        //new一个固定的线程池。Executors是线程池的一个工具类
        //Executor executor = Executors.newFixedThreadPool(2);
        //不带线程工厂
        //ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
        //        2,                      //核心线程数量
        //        3,                        //最大线程数量
        //        1000,                   //线程最大保持时间
        //        TimeUnit.MILLISECONDS,      //时间单位
        //        new ArrayBlockingQueue(3)); //线程阻塞队列
        //带线程工厂
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                1,                      //核心线程数量
                2,                  //最大线程数量
                1000,                   //线程最大保持时间
                TimeUnit.MILLISECONDS,                  //时间单位
                new ArrayBlockingQueue(2),  //线程阻塞队列
                Executors.defaultThreadFactory());      //线程工厂

        threadPoolExecutor.execute(new MyThread("work:1"));
        threadPoolExecutor.execute(new MyThread("work:2"));

        Thread.sleep(1000);
        threadPoolExecutor.shutdown();
    }

    static class MyThread implements Runnable{
        private String workId;

        public MyThread(String s) {
            workId = s;
        }

        @Override
        public void run() {
            int cnt = 0;
            while (cnt < 10) {
                System.out.println(workId + "-count:" + cnt++);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
