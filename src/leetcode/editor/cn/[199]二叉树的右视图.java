package leetcode.editor.cn;
//给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
//
// 示例: 
//
// 输入: [1,2,3,null,5,null,4]
//输出: [1, 3, 4]
//解释:
//
//   1            <---
// /   \
//2     3         <---
// \     \
//  5     4       <---
// 
// Related Topics 树 深度优先搜索 广度优先搜索

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

class RightSideView {
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
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) {
                return new ArrayList<Integer>();
            }
            List<Integer> rs = new ArrayList<>();
            Queue<List<TreeNode>> queue = new LinkedBlockingQueue<>();
            queue.add(Arrays.asList(root));
            while (!queue.isEmpty()) {
                List<TreeNode> p = queue.poll();
                rs.add(p.get(p.size() - 1).val);
                List<TreeNode> c = new ArrayList<>();
                for (TreeNode treeNode : p) {
                    if (treeNode.left != null) {
                        c.add(treeNode.left);
                    }
                    if (treeNode.right != null) {
                        c.add(treeNode.right);
                    }
                }
                if (!c.isEmpty()) {
                    queue.add(c);
                }
            }
            return rs;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new RightSideView().new Solution();
    }
}