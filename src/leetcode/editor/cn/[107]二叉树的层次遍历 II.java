package leetcode.editor.cn;
//给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
//
// 例如： 
//给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7
// 
//
// 返回其自底向上的层次遍历为： 
//
// [
//  [15,7],
//  [9,20],
//  [3]
//]
// 
// Related Topics 树 广度优先搜索


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;

class LevelOrderBottom {

    //Definition for a binary tree node.
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> list = new ArrayList<>();
            Queue<TreeNode> queue = new LinkedBlockingQueue<>();
            if (root == null) {
                return list;
            }

            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<TreeNode> t = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    t.add(queue.poll());
                }
                List<Integer> collect = t.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
                list.add(collect);

                for (TreeNode treeNode : t) {
                    if (treeNode.left != null) {
                        queue.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        queue.add(treeNode.right);
                    }
                }
            }
            Collections.reverse(list);
            return list;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        LevelOrderBottom levelOrderBottom = new LevelOrderBottom();
        Solution solu = levelOrderBottom.new Solution();
        TreeNode t1 = levelOrderBottom.new TreeNode(3);
        TreeNode t2 = levelOrderBottom.new TreeNode(9);
        TreeNode t3 = levelOrderBottom.new TreeNode(20);
        TreeNode t4 = levelOrderBottom.new TreeNode(15);
        TreeNode t5 = levelOrderBottom.new TreeNode(7);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;

        List<List<Integer>> lists = solu.levelOrderBottom(t1);
        for (List<Integer> list : lists) {
            System.out.println(list.toString());
        }
    }

}