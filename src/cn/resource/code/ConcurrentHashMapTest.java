package cn.resource.code;

import java.util.concurrent.ConcurrentHashMap;

/*
 * @DATE 2021/5/24 19:00
 * @AUTH cuijiuifeng
 * @DESC
 */
/*
//初始化数组
private final Node<K,V>[] initTable() {
    Node<K,V>[] tab; int sc;
    while ((tab = table) == null || tab.length == 0) {
        //如果多个线程同时在初始化(保证不会被同时执行初始化操作)
        if ((sc = sizeCtl) < 0)
            //线程让步
            Thread.yield(); // lost initialization race; just spin
        //如果有机会获取锁，则设置状态为初始化状态
        else if (U.compareAndSwapInt(this, SIZECTL, sc, -1)) {
            try {
                //如果table为null，则进行初始化(这里的if类似双重检查的作用，当多个线程同时走过上边的if，同时到达else if竞争锁时，其中一个线程获得锁并初始化table之后，
                //要保证其它线程不会再初始化table了)
                if ((tab = table) == null || tab.length == 0) {
                    int n = (sc > 0) ? sc : DEFAULT_CAPACITY;
                    @SuppressWarnings("unchecked")
                    Node<K,V>[] nt = (Node<K,V>[])new Node<?,?>[n];
                    table = tab = nt;
                    //sc=table大小的四分之三
                    sc = n - (n >>> 2);
                }
            } finally {
                //把sc赋给sizeCtl，else if里等待锁的线程能够继续往下执行，从而执行到break跳出for循环
                sizeCtl = sc;
            }
            break;
        }
    }
    return tab;
}
final V putVal(K key, V value, boolean onlyIfAbsent) {
    //如果k或v为null抛异常
    if (key == null || value == null) throw new NullPointerException();
    //计算hash值(spread为散列算法)
    int hash = spread(key.hashCode());
    int binCount = 0;
    for (Node<K,V>[] tab = table;;) {
        Node<K,V> f; int n, i, fh;
        //如果table为null则初始化(类似于hashmap的实现原理为数组+链表[数组+红黑树])
        if (tab == null || (n = tab.length) == 0)
            tab = initTable();
        //i位置就是元素即将放入的位置，如果此位置上没有元素，则进入
        else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) {
            //轻量级-无锁并发写值(设置node-包含k,v,next,hash)
            if (casTabAt(tab, i, null,
                         new Node<K,V>(hash, key, value, null)))
                break;                   // no lock when adding to empty bin
        }
        //
        else if ((fh = f.hash) == MOVED)
            tab = helpTransfer(tab, f);
        //如果i位置上已经有元素了
        else {
            V oldVal = null;
            //锁定桶结点(数组中的一个元素结点)
            synchronized (f) {
                //获取锁之后再次检查
                if (tabAt(tab, i) == f) {
                    if (fh >= 0) {
                        //链表转换红黑树的阈值
                        binCount = 1;
                        //遍历桶中的结点
                        for (Node<K,V> e = f;; ++binCount) {
                            K ek;
                            //如果key是同一对象或者key相等
                            if (e.hash == hash &&
                                ((ek = e.key) == key ||
                                 (ek != null && key.equals(ek)))) {
                                oldVal = e.val;
                                //onlyIfAbsent用于判断，如果存在key相同的时候是否覆盖value值
                                if (!onlyIfAbsent)
                                    e.val = value;
                                break;
                            }
                            Node<K,V> pred = e;
                            //将新的元素放入链表的最后边
                            if ((e = e.next) == null) {
                                pred.next = new Node<K,V>(hash, key,
                                                          value, null);
                                break;
                            }
                        }
                    }
                    //如果桶中的元素为红黑树
                    else if (f instanceof TreeBin) {
                        Node<K,V> p;
                        binCount = 2;
                        if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                       value)) != null) {
                            oldVal = p.val;
                            if (!onlyIfAbsent)
                                p.val = value;
                        }
                    }
                }
            }
            if (binCount != 0) {
                //如果达到阈值则将链表转换为红黑树
                if (binCount >= TREEIFY_THRESHOLD)
                    treeifyBin(tab, i);
                if (oldVal != null)
                    return oldVal;
                break;
            }
        }
    }
    //设置map大小及调整大小等
    addCount(1L, binCount);
    return null;
}
*/
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<Object, String> map = new ConcurrentHashMap<>(1);
        map.put(new MyObj(), "aaa");
        map.put(new MyObj(), "bbb");
    }

    public static class MyObj {
        @Override
        public int hashCode() {
            return 1;
        }

        @Override
        public boolean equals(Object obj) {
            return false;
        }
    }
}
