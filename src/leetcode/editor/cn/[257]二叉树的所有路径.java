//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 
// 👍 486 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2021-04-16 16:12:42
 * @auth cui
 */
class BinaryTreePaths {

    /**
     * Definition for a binary tree node.
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> binaryTreePaths(TreeNode root) {
            List<String> list = new ArrayList<>();
            f(list, root, null);
            return list;
        }

        public void f(List<String> list, TreeNode node, String cur) {
            if (node.left == null && node.right == null) {
                list.add(cur == null ? (node.val + "") : (cur + "->" + node.val));
            }
            if (node.left != null) {
                f(list, node.left, cur == null ? (node.val + "") : (cur + "->" + node.val));
            }
            if (node.right != null) {
                f(list, node.right, cur == null ? (node.val + "") : cur + "->" + node.val);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new BinaryTreePaths().new Solution();
        TreeNode treeNode = new TreeNode(1, new TreeNode(2, null, new TreeNode(5, null, null)), new TreeNode(3, null, null));
        System.out.println(solution.binaryTreePaths(treeNode));
    }
}