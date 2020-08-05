package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

/**
 * 读hashmap
 * @ClassName: TestHashMap
 * @Date: 2020/8/5 10:34
 * @auth: Administrator
 */
/*
    hashmap中的transient Node<K,V>[] table;为真正存数据的，可以看出，hashmap在实现时是数组+链表+红黑树的实现方式，链表长度达到某一阈值会转换成红黑树
    红黑树，是一棵自平衡的二叉查找树，红黑树不是严格意义上的平衡二叉树。
    二叉查找树，左子树小于根结点，右子树大于根结点，递归定义
    二叉平衡树，左右子树的高度相差不超过1，递归定义
    //链表结点
    static class Node<K,V> implements Map.Entry<K,V> {
        final int hash;         //元素的hash值
        final K key;            //元素的key
        V value;                //元素的值value
        Node<K,V> next;         //下一个
    }
    //树结点
    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;  // red-black tree links
        TreeNode<K,V> left;
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        boolean red;
    }
    1。put
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    2。计算key的hash值
    static final int hash(Object key) {
        int h;
        //hashcode异或上hashcode无符号右移16位
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
    3。put的核心代码
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent, boolean evict) {
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        //如果此时table为空，则重新调整大小，会在resize里重新分配一个Node<K,V>[] table;数组
        if ((tab = table) == null || (n = tab.length) == 0)
            n = (tab = resize()).length;
        //如果table表(并上(n-1)表示不能超出table的大小)的hash位置是空的，则直接放到hash位置
        if ((p = tab[i = (n - 1) & hash]) == null)
            tab[i] = newNode(hash, key, value, null);
        //如果hash位置上已经是有元素的了
        else {
            Node<K,V> e; K k;
            //hash相同且key相同
            if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
                e = p;
            //如果p是TreeNode结点
            else if (p instanceof TreeNode)
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            //如果p不是TreeNode结点
            else {
                for (int binCount = 0; ; ++binCount) {
                    //如果p的下一个元素是空了
                    if ((e = p.next) == null) {
                        //把新添加的元素放p的后面
                        p.next = newNode(hash, key, value, null);
                        //如果链表的长度达到阈值，则升级成树
                        if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
                            treeifyBin(tab, hash);
                        break;
                    }
                    //hash相同且key相同则break
                    if (e.hash == hash && ((k = e.key) == key || (key != null && key.equals(k))))
                        break;
                    //链表继续向后移动
                    p = e;
                }
            }
            //如果存在相同key的元素
            if (e != null) {
                V oldValue = e.value;
                //设置新的值
                if (!onlyIfAbsent || oldValue == null)
                    e.value = value;
                //回调方法
                afterNodeAccess(e);
                return oldValue;
            }
        }
        ++modCount;
        //map的大小加一，如果超出阈值，则重新调整大小。如果此时不重新调整大小，就会出现频繁的hash冲突
        if (++size > threshold)
            resize();
        //回调方法
        afterNodeInsertion(evict);
        return null;
    }
    4。将链表结点转换成树结点
    final void treeifyBin(Node<K,V>[] tab, int hash) {
        int n, index; Node<K,V> e;
        //如果table此时的大小还是很大，则先调整大小，以减少hash冲突
        if (tab == null || (n = tab.length) < MIN_TREEIFY_CAPACITY)
            resize();
        //如果hash位置位置的元素不为空，
        else if ((e = tab[index = (n - 1) & hash]) != null) {
            TreeNode<K,V> hd = null, tl = null;
            do {
                //把链表结点换成树结点
                TreeNode<K,V> p = replacementTreeNode(e, null);
                //
                if (tl == null)
                    hd = p;
                else {
                    p.prev = tl;
                    tl.next = p;
                }
                tl = p;
            //遍历链表中的所有结点
            } while ((e = e.next) != null);
            //调整树
            if ((tab[index] = hd) != null)
                hd.treeify(tab);
        }
    }
    5。
    final void treeify(Node<K,V>[] tab) {
        TreeNode<K,V> root = null;
        //遍历所有的结点
        for (TreeNode<K,V> x = this, next; x != null; x = next) {
            next = (TreeNode<K,V>)x.next;
            x.left = x.right = null;
            //设置父结点
            if (root == null) {
                x.parent = null;
                x.red = false;
                root = x;
            }
            else {
                K k = x.key;
                int h = x.hash;
                Class<?> kc = null;
                //遍历已有的树
                for (TreeNode<K,V> p = root;;) {
                    int dir, ph;
                    K pk = p.key;
                    //如果hash值小于父结点，往左放
                    if ((ph = p.hash) > h)
                        dir = -1;
                    //如果hash值大于父结点，往右放
                    else if (ph < h)
                        dir = 1;
                    //根据key比较
                    else if ((kc == null &&
                              (kc = comparableClassFor(k)) == null) ||
                             (dir = compareComparables(kc, k, pk)) == 0)
                        dir = tieBreakOrder(k, pk);

                    TreeNode<K,V> xp = p;
                    if ((p = (dir <= 0) ? p.left : p.right) == null) {
                        //设置父结点
                        x.parent = xp;
                        //把结点设置为左子树
                        if (dir <= 0)
                            xp.left = x;
                        //把结点设置为右子树
                        else
                            xp.right = x;
                        //此处应该是平衡树
                        root = balanceInsertion(root, x);
                        break;
                    }
                }
            }
        }
        moveRootToFront(tab, root);
    }
*/
public class TestHashMap {
    public static void main(String[] args){
        Map<MyInt, String> map = new HashMap<>();
        for (int i = 0; i < 20; i++) {
            map.put(new MyInt(i), "haha"+i);
        }
    }
    //下面的类是为了放入map中的元素让他的hash相同，但又不能是同一对象
    static class MyInt{
        Integer i;
        public MyInt(Integer i) {
            this.i = i;
        }
        @Override
        public boolean equals(Object o) {
            return false;
        }
        @Override
        public int hashCode() {
            return 1;
        }
    }
}
