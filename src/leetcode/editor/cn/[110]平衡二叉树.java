package leetcode.editor.cn;
//给定一个二叉树，判断它是否是高度平衡的二叉树。
//
// 本题中，一棵高度平衡二叉树定义为： 
//
// 
// 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。 
// 
//
// 示例 1: 
//
// 给定二叉树 [3,9,20,null,null,15,7] 
//
//     3
//   / \
//  9  20
//    /  \
//   15   7 
//
// 返回 true 。 
// 
//示例 2: 
//
// 给定二叉树 [1,2,2,3,3,null,null,4,4] 
//
//        1
//      / \
//     2   2
//    / \
//   3   3
//  / \
// 4   4
// 
//
// 返回 false 。 
//
// 
// Related Topics 树 深度优先搜索

class IsBalanced {
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


        public boolean isBalanced(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (Math.abs(getHeight(root.left) - getHeight(root.right)) > 1) {
                return false;
            }
            return isBalanced(root.left) && isBalanced(root.right);
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return 0;
            }
            return Math.max(getHeight(root.left) + 1, getHeight(root.right) + 1);
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        IsBalanced isBalanced = new IsBalanced();
        Solution solu = isBalanced.new Solution();
        TreeNode t1 = isBalanced.new TreeNode(1);
        TreeNode t2 = isBalanced.new TreeNode(2);
        TreeNode t3 = isBalanced.new TreeNode(2);
        TreeNode t4 = isBalanced.new TreeNode(3);
        TreeNode t5 = isBalanced.new TreeNode(3);
        TreeNode t6 = isBalanced.new TreeNode(4);
        TreeNode t7 = isBalanced.new TreeNode(4);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t3.right = t5;
        t4.left = t6;
        t5.right = t7;
        //1,2,2,3,null,null,3,4,null,null,4
        System.out.println(solu.isBalanced(t1));
    }
}