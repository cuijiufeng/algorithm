package leetcode.editor.cn;
//删除链表中等于给定值 val 的所有节点。
//
// 示例: 
//
// 输入: 1->2->6->3->4->5->6, val = 6
//输出: 1->2->3->4->5
// 
// Related Topics 链表 
// 👍 437 👎 0

class RemoveElements {
    //Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            return "val=" + val + ',';
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode removeElements(ListNode head, int val) {
            if (head == null) {
                return null;
            }
            ListNode t = head;
            while (t != null && t.val == val) {
                t = t.next;
                head = t;
            }
            if (t == null) {
                return null;
            }
            while (t != null && t.next != null) {
                ListNode a = t.next;
                if (a.val == val) {
                    t.next = a.next;
                } else {
                    t = t.next;
                }
            }
            return head;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        RemoveElements removeElements = new RemoveElements();
        Solution solution = removeElements.new Solution();
        ListNode l1 = removeElements.new ListNode(1);
        ListNode l2 = removeElements.new ListNode(2);
        ListNode l3 = removeElements.new ListNode(2);
        ListNode l4 = removeElements.new ListNode(1);
        //ListNode l5 = new ListNode(5);
        //ListNode l6 = new ListNode(6);
        l1.next = l2;
        l2.next = l3;
        l3.next = l4;
        //l4.next = l5;
        //l5.next = l6;
        System.out.println(solution.removeElements(l1, 2));
    }
}