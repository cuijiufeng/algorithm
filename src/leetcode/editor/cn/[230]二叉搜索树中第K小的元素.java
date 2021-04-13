package leetcode.editor.cn;
//给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
//
// 说明： 
//你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。 
//
// 示例 1: 
//
// 输入: root = [3,1,4,null,2], k = 1
//   3
//  / \
// 1   4
//  \
//   2
//输出: 1 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], k = 3
//       5
//      / \
//     3   6
//    / \
//   2   4
//  /
// 1
//输出: 3 
//
// 进阶： 
//如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？ 
// Related Topics 树 二分查找


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class KthSmallest {
    //Definition for a binary tree node.
    class TreeNode {
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
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            List<TreeNode> list = new ArrayList<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            while (!stack.isEmpty()) {
                TreeNode t = stack.pop();
                list.add(t);
                if (t.right != null) {
                    t = t.right;
                    while (t != null) {
                        stack.push(t);
                        t = t.left;
                    }
                }
            }
            return list.get(k - 1).val;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        Solution solu = kthSmallest.new Solution();
        TreeNode t1 = kthSmallest.new TreeNode(3);
        TreeNode t2 = kthSmallest.new TreeNode(1);
        TreeNode t3 = kthSmallest.new TreeNode(4);
        TreeNode t4 = kthSmallest.new TreeNode(2);
        t1.left = t2;
        t1.right = t3;
        t2.right = t4;
        solu.kthSmallest(t1, 1);
    }
}