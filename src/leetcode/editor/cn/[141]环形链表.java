package leetcode.editor.cn;
//给定一个链表，判断链表中是否有环。
//
// 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。 
//
// 
//
// 示例 1： 
//
// 输入：head = [3,2,0,-4], pos = 1
//输出：true
//解释：链表中有一个环，其尾部连接到第二个节点。
// 
//
// 
//
// 示例 2： 
//
// 输入：head = [1,2], pos = 0
//输出：true
//解释：链表中有一个环，其尾部连接到第一个节点。
// 
//
// 
//
// 示例 3： 
//
// 输入：head = [1], pos = -1
//输出：false
//解释：链表中没有环。
// 
//
// 
//
// 
//
// 进阶： 
//
// 你能用 O(1)（即，常量）内存解决此问题吗？ 
// Related Topics 链表 双指针

import java.util.ArrayList;
import java.util.List;

class HasCycle {

    //Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public boolean hasCycle(ListNode head) {
            if (head == null) {
                return false;
            }
            List<ListNode> list = new ArrayList<>();
            ListNode ln = head;
            while (ln.next != null) {
                if (list.contains(ln)) {
                    return true;
                }
                list.add(ln);
                ln = ln.next;
            }
            return false;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        HasCycle hasCycle = new HasCycle();
        Solution solu =hasCycle. new Solution();
        ListNode ln1 = hasCycle.new ListNode(1);
        ListNode ln2 = hasCycle.new ListNode(2);
        ln1.next = ln2;
        ln2.next = ln1;
        System.out.println(solu.hasCycle(ln1));
    }
}