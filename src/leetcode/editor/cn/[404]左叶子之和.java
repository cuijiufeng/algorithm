//给定二叉树的根节点 root ，返回所有左叶子之和。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入: root = [3,9,20,null,null,15,7] 
//输出: 24 
//解释: 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
// 
//
// 示例 2: 
//
// 
//输入: root = [1]
//输出: 0
// 
//
// 
//
// 提示: 
//
// 
// 节点数在 [1, 1000] 范围内 
// -1000 <= Node.val <= 1000 
// 
//
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 413 👎 0


package leetcode.editor.cn;

import utils.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

class SumOfLeftLeaves {
    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int sumOfLeftLeaves(TreeNode root) {
            return sumOfLeftLeaves0(root, false);
        }
        public int sumOfLeftLeaves0(TreeNode root, boolean isLeft) {
            if (root == null) {
                return 0;
            }
            if (root.right == null && root.left == null) {
                return isLeft ? root.val : 0;
            }
            return sumOfLeftLeaves0(root.left, true) + sumOfLeftLeaves0(root.right, false);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new SumOfLeftLeaves().new Solution();
        Integer[] vals = {0,-4,-3,null,-1,8,null,null,3,null,-9,-2,null,4};
        TreeNode root = TreeUtils.arrtyToTree(0, vals, TreeNode.class);
        System.out.println(solution.sumOfLeftLeaves(root));
    }
}