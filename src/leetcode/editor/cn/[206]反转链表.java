package leetcode.editor.cn;
//反转一个单链表。
//
// 示例: 
//
// 输入: 1->2->3->4->5->NULL
//输出: 5->4->3->2->1->NULL 
//
// 进阶: 
//你可以迭代或递归地反转链表。你能否用两种方法解决这道题？ 
// Related Topics 链表



//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

// th
// 2->  1->3->4->5->NULL
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        ListNode ln1 = new ListNode(1);
        ListNode ln2 = new ListNode(2);
        ListNode ln3 = new ListNode(3);
        ListNode ln4 = new ListNode(4);
        ListNode ln5 = new ListNode(5);
        ln1.next = ln2;
        ln2.next = ln3;
        ln3.next = ln4;
        ln4.next = ln5;
        ListNode listNode = solu.reverseList(ln1);
        while (listNode != null){
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    //循环
    public ListNode reverseList(ListNode head) {
        if (head == null){
            return head;
        }
        ListNode t, p = head;
        while (p.next != null){
            t = p.next;
            p.next = t.next;
            t.next = head;
            head = t;
        }
        return head;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
