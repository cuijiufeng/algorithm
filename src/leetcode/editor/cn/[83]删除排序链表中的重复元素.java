package leetcode.editor.cn;
//给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
//
// 示例 1: 
//
// 输入: 1->1->2
//输出: 1->2
// 
//
// 示例 2: 
//
// 输入: 1->1->2->3->3
//输出: 1->2->3 
// Related Topics 链表


class DeleteDuplicates {
    public static void main(String[] args) {

    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    //Definition for singly-linked list.
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode deleteDuplicates(ListNode head) {
            ListNode p1 = head, p2 = head;
            while (p1 != null) {
                while (p2.next != null) {
                    if (p2.next.val == p1.val) {
                        p2.next = p2.next.next;
                    } else {
                        p2 = p2.next;
                    }
                }
                p1 = p1.next;
                p2 = p1;
            }
            return head;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

