//如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。 
//
// 只有给定的树是单值二叉树时，才返回 true；否则返回 false。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：[1,1,1,1,1,null,1]
//输出：true
// 
//
// 示例 2： 
//
// 
//
// 输入：[2,2,2,5,2]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 给定树的节点数范围是 [1, 100]。 
// 每个节点的值都是整数，范围为 [0, 99] 。 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 二叉树 
// 👍 123 👎 0


package leetcode.editor.cn;

import utils.TreeUtils;

import java.util.Stack;

class UnivaluedBinaryTree {

//Definition for a binary tree node.
public static class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isUnivalTree(TreeNode root) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode loop = root;
            while (loop != null) {
                if (loop.val != root.val) {
                    return false;
                }
                stack.push(loop);
                loop = loop.left;
            }
            while (!stack.isEmpty()) {
                loop = stack.pop().right;
                while (loop != null) {
                    if (loop.val != root.val) {
                        return false;
                    }
                    stack.push(loop);
                    loop = loop.left;
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new UnivaluedBinaryTree().new Solution();
        TreeNode root = TreeUtils.arrtyToTree(0, new Integer[]{1, 1, 1, 1, 1, null, 1}, TreeNode.class);
        System.out.println(solution.isUnivalTree(root));
    }
}