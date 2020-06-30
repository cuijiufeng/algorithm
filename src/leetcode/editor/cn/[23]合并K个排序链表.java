package leetcode.editor.cn;
//合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
//
// 示例: 
//
// 输入:
//[
//  1->4->5,
//  1->3->4,
//  2->6
//]
//输出: 1->1->2->3->4->4->5->6 
// Related Topics 堆 链表 分治算法

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode head = new ListNode(-1);
        head.next = null;
        ListNode t = head;
        ListNode[] p = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            p[i] = lists[i];
        }
        while(!listNodeArrIsAllNull(p)){
            int pos = -1;
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < p.length; i++) {
                if(p[i] != null && p[i].val<min){
                    min = p[i].val;
                    pos = i;
                }
            }
            t.next = new ListNode(min);
            t = t.next;
            t.next = null;
            p[pos] = p[pos].next;
        }
        return head.next;
    }

    private boolean listNodeArrIsAllNull(ListNode[] p) {
        for (ListNode listNode : p) {
            if(listNode!=null){
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
