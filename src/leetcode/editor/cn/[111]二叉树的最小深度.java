package leetcode.editor.cn;
//给定一个二叉树，找出其最小深度。
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 给定二叉树 [3,9,20,null,null,15,7], 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回它的最小深度 2. 
// Related Topics 树 深度优先搜索 广度优先搜索

class MinDepth {

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
//Definition for a binary tree node.
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) {
                return 0;
            }
            if (root.left == null && root.right == null) {
                return 1;
            }
            if (root.left == null && root.right != null) {
                return minDepth(root.right) + 1;
            } else if (root.left != null && root.right == null) {
                return minDepth(root.left) + 1;
            } else {
                return Math.min(minDepth(root.left) + 1, minDepth(root.right) + 1);
            }
        }

    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solu = new MinDepth().new Solution();
        TreeNode tn1 = new MinDepth().new TreeNode(1);
        tn1.left = null;
        tn1.right = new MinDepth().new TreeNode(2);
        tn1.right.left = null;
        tn1.right.right = null;
        System.out.println(solu.minDepth(tn1));
    }
}