package leetcode.editor.cn;
//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
// 示例：
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
// Related Topics 链表

class MergeTwoLists {
    //Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode ln = new ListNode(-1, null), rs = ln;
            while (l1 != null && l2 != null) {
                if (l1.val <= l2.val) {
                    ln.next = new ListNode(l1.val, null);
                    l1 = l1.next;
                    ln = ln.next;
                } else {
                    ln.next = new ListNode(l2.val, null);
                    l2 = l2.next;
                    ln = ln.next;
                }
            }
            while (l1 != null) {
                ln.next = new ListNode(l1.val, null);
                l1 = l1.next;
                ln = ln.next;
            }
            while (l2 != null) {
                ln.next = new ListNode(l2.val, null);
                l2 = l2.next;
                ln = ln.next;
            }
            return rs.next;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        MergeTwoLists mergeTwoLists = new MergeTwoLists();
        Solution solution = mergeTwoLists.new Solution();
        solution.mergeTwoLists(mergeTwoLists.new ListNode(), mergeTwoLists.new ListNode());
    }
}
