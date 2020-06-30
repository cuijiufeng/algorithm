package leetcode.editor.cn;
//给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
//
// 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。 
//
//  -1 1 2  4 3
//   f   s
// 示例: 
//
// 给定 1->2->3->4, 你应该返回 2->1->4->3.
// 
// Related Topics 链表

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode lnHead = new ListNode(-1);
        lnHead.next = head;
        ListNode first = lnHead, second = lnHead.next;
        while(second != null && second.next != null){
            first.next = second.next;
            second.next = second.next.next;
            first.next.next = second;
            first = second;
            second = first.next;
        }
        return lnHead.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
