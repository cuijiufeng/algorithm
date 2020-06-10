package cn.algo.demo;

/**
 * @ClassName: AlgoDay2_3
 * @Description: day2_3
 * @Date: 2020/6/10 15:32
 * @auth: Administrator
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class AlgoDay2_3 {
    public static void main(String[] args){
        ListNode l1 = new ListNode(2);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(3);
        ListNode l4 = new ListNode(5);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(4);
        l1.next = l2;
        l2.next = l3;
        l3.next = null;
        l4.next = l5;
        l5.next = l6;
        l6.next = null;

        ListNode l7 = new ListNode(9);
        ListNode l8 = new ListNode(9);
        l7.next =  l8;
        l8.next =  null;
        ListNode l9 = new ListNode(1);
        l9.next = null;
        AlgoDay2_3 algoDay2_3 = new AlgoDay2_3();
        ListNode nodes = algoDay2_3.addTwoNumbers(l1, l4);
        while(nodes != null){
            System.out.print(nodes.val+"-->");
            nodes = nodes.next;
        }
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        //进位标示
        boolean flag = false;
        ListNode ln1 = l1, ln2 = l2, lnRs = new ListNode(-1), lnt = lnRs;

        //开始去处
        while(ln1!=null || ln2!=null){
            //生成一个结点,放在链表的尾
            lnRs.next = new ListNode(0);
            lnRs = lnRs.next;
            lnRs.next = null;

            //加上上一次运算的进位，个位数加法最多进位1
            if(flag){
                lnRs.val += 1;
            }
            flag = false;
            //如果不空
            if(ln1 != null){
                lnRs.val += ln1.val;
            }
            //如果不空
            if(ln2 != null){
                lnRs.val += ln2.val;
            }
            //如果有进位
            if(lnRs.val/10 != 0){
                flag = true;
                //去除进位
                lnRs.val %= 10;
            }
            //下一位数字
            if(ln1 != null){
                ln1 = ln1.next;
            }
            if(ln2 != null){
                ln2 = ln2.next;
            }
        }
        //如果位数都没有了还有进位也要加上
        if(flag){
            lnRs.next = new ListNode(1);
            lnRs = lnRs.next;
            lnRs.next = null;
        }
        return lnt.next;
    }
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
