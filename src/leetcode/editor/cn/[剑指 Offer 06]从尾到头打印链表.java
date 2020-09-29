//输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。 
//
// 
//
// 示例 1： 
//
// 输入：head = [1,3,2]
//输出：[2,3,1] 
//
// 
//
// 限制： 
//
// 0 <= 链表长度 <= 10000 
// Related Topics 链表 
// 👍 60 👎 0

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] reversePrint(ListNode head) {
        List list = new LinkedList();
        while (head != null) {
            list.add(0, head.val);
            head = head.next;
        }
        int[] nums = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            nums[i] = (int) list.get(i);
        }
        return nums;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
