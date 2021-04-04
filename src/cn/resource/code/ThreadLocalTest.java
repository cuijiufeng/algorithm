package cn.resource.code;

/**
 * ThreadLocal为每个线程维护变量，不对其他线程可见，
 * 其实它是把变量放在了Thread中的，所以线程隔离开了
 * @ClassName: ThreadLocalTest
 * @Date: 2020/9/18 13:48
 * @auth: Administrator
 * public void set(T value) {
 *          //获取当前的线程
 *         Thread t = Thread.currentThread();
 *         //Thread类中有ThreadLocal.ThreadLocalMap类型的threadLocals属性
 *         //ThreadLocal.ThreadLocalMap类中有一个属性Entry[] table;用来存放数据
 *         ThreadLocalMap map = getMap(t);
 *         //如果map为null则创建
 *         if (map != null)
 *             map.set(this, value);
 *         else
 *             createMap(t, value);
 *     }
 * private void set(ThreadLocal<?> key, Object value) {
 *         Entry[] tab = table;
 *         int len = tab.length;
 *         //计算数据的存放位置
 *         int i = key.threadLocalHashCode & (len-1);
 *         //遍历
 *         for (Entry e = tab[i]; e != null; e = tab[i = nextIndex(i, len)]) {
 *              //取ThreadLocal
 *             ThreadLocal<?> k = e.get();
 *             //如果key一样，则覆盖value
 *             if (k == key) {
 *                 e.value = value;
 *                 return;
 *             }
 *             //如果key为null
 *             if (k == null) {
 *                 replaceStaleEntry(key, value, i);
 *                 return;
 *             }
 *         }
 *         //如果i位置为null，则直接存放
 *         tab[i] = new Entry(key, value);
 *         int sz = ++size;
 *         if (!cleanSomeSlots(i, sz) && sz >= threshold)
 *             rehash();
 *     }
 * public T get() {
 *          //获取当前的线程
 *         Thread t = Thread.currentThread();
 *         //获得Thread的threadLocals
 *         ThreadLocalMap map = getMap(t);
 *         if (map != null) {
 *              //从map中获取ThreadLocal对应的Entry
 *             ThreadLocalMap.Entry e = map.getEntry(this);
 *             //如果存在则返回数据
 *             if (e != null) {
 *                 T result = (T)e.value;
 *                 return result;
 *             }
 *         }
 *         //如果map为null，则返回null，并创建map
 *         return setInitialValue();
 *     }
 * void createMap(Thread t, T firstValue) {
 *         t.threadLocals = new ThreadLocalMap(this, firstValue);
 *     }
 * ThreadLocalMap(ThreadLocal<?> firstKey, Object firstValue) {
 *          //创建对象数组
 *          table = new Entry[INITIAL_CAPACITY];
 *          //计算数组存放的位置
 *          int i = firstKey.threadLocalHashCode & (INITIAL_CAPACITY - 1);
 *          //设置数据进去。firstKey是当前ThreadLocal对象，firstValue为要放入的值
 *          table[i] = new Entry(firstKey, firstValue);
 *          size = 1;
 *          setThreshold(INITIAL_CAPACITY);
 *      }
 */
public class ThreadLocalTest {
    private ThreadLocal threadLocal = new ThreadLocal();;

    public static void main(String[] args){
        ThreadLocalTest test = new ThreadLocalTest();
        test.f();
    }

    public void f(){
        new Thread(new MyThread1()).start();
        new Thread(new MyThread2()).start();
    }

    public class MyThread1 implements Runnable{
        @Override
        public void run() {
            threadLocal.set(100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }

    public class MyThread2 implements Runnable{
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }
}
