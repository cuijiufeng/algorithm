package leetcode.editor.cn;
//给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
//
// 
//
// 示例： 
//二叉树：[3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其层次遍历结果： 
//
// [
//  [3],
//  [9,20],
//  [15,7]
//]
// 
// Related Topics 树 广度优先搜索


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;


//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)s
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t2.left = t3;
        t3.left = t4;
        t4.left = t5;

        for (List<Integer> integers : solu.levelOrder(t1)) {
            System.out.println(integers.toString());
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        if (root == null){
            return list;
        }

        queue.add(root);
        while (!queue.isEmpty()){
            //队列的大小就是一层中结点的个数
            int size = queue.size();
            List<TreeNode> t = new ArrayList<>();
            //把一层中的结点都取出来
            for (int i = 0; i < size; i++) {
                t.add(queue.poll());
            }
            List<Integer> collect = t.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
            list.add(collect);

            //遍历这一层中的结点，如果有左右子树，全部进队
            for (TreeNode treeNode : t) {
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null){
                    queue.add(treeNode.right);
                }
            }
        }
        return list;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
