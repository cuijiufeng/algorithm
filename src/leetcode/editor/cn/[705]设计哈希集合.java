//不使用任何内建的哈希表库设计一个哈希集合（HashSet）。 
//
// 实现 MyHashSet 类： 
//
// 
// void add(key) 向哈希集合中插入值 key 。 
// bool contains(key) 返回哈希集合中是否存在这个值 key 。 
// void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。 
// 
// 
//
// 示例： 
//
// 
//输入：
//["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove
//", "contains"]
//[[], [1], [2], [1], [3], [2], [2], [2], [2]]
//输出：
//[null, null, null, true, false, null, true, null, false]
//
//解释：
//MyHashSet myHashSet = new MyHashSet();
//myHashSet.add(1);      // set = [1]
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(1); // 返回 True
//myHashSet.contains(3); // 返回 False ，（未找到）
//myHashSet.add(2);      // set = [1, 2]
//myHashSet.contains(2); // 返回 True
//myHashSet.remove(2);   // set = [1]
//myHashSet.contains(2); // 返回 False ，（已移除） 
//
// 
//
// 提示： 
//
// 
// 0 <= key <= 106 
// 最多调用 104 次 add、remove 和 contains 。 
// 
//
// 
//
// 进阶：你可以不使用内建的哈希集合库解决此问题吗？ 
// Related Topics 设计 哈希表 
// 👍 153 👎 0

package leetcode.editor.cn;

import java.util.LinkedList;

/**
 * @date 2021-04-04 18:13:50
 * @auth cui
 */
class DesignHashset {
    //leetcode submit region begin(Prohibit modification and deletion)
    class MyHashSet {
        private LinkedList<Integer>[] element;
        private static final int COUNT = 1024;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            element = new LinkedList[COUNT];
        }

        public void add(int key) {
            if (element[key%COUNT] == null){
                element[key%COUNT] = new LinkedList<>();
            }
            if (!element[key%COUNT].contains(key)){
                element[key%COUNT].add(key);
            }
        }

        public void remove(int key) {
            if (element[key%COUNT] == null) {
                return;
            }
            if (element[key%COUNT].contains(key)){
                element[key%COUNT].remove((Object)key);
            }
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            if (element[key%COUNT] == null){
                return false;
            }
            return element[key%COUNT].contains(key);
        }
    }

    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     */
//leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MyHashSet myHashSet = new DesignHashset().new MyHashSet();
        myHashSet.add(9);      // set = [1]
        myHashSet.remove(19);      // set = [1, 2]
        myHashSet.add(14);      // set = [1]
        myHashSet.remove(19);      // set = [1, 2]
        myHashSet.remove(9);      // set = [1, 2]
        myHashSet.add(0);      // set = [1, 2]
        myHashSet.add(3);      // set = [1, 2]
        myHashSet.add(4);      // set = [1, 2]
        myHashSet.add(0);      // set = [1, 2]
        myHashSet.remove(9);      // set = [1, 2]
    }
}