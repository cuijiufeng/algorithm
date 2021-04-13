package leetcode.editor.cn;
//给定一个二叉树，它的每个结点都存放一个 0-9 的数字，每条从根到叶子节点的路径都代表一个数字。
//
// 例如，从根到叶子节点路径 1->2->3 代表数字 123。 
//
// 计算从根到叶子节点生成的所有数字之和。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//    1
//   / \
//  2   3
//输出: 25
//解释:
//从根到叶子节点路径 1->2 代表数字 12.
//从根到叶子节点路径 1->3 代表数字 13.
//因此，数字总和 = 12 + 13 = 25. 
//
// 示例 2: 
//
// 输入: [4,9,0,5,1]
//    4
//   / \
//  9   0
// / \
//5   1
//输出: 1026
//解释:
//从根到叶子节点路径 4->9->5 代表数字 495.
//从根到叶子节点路径 4->9->1 代表数字 491.
//从根到叶子节点路径 4->0 代表数字 40.
//因此，数字总和 = 495 + 491 + 40 = 1026. 
// Related Topics 树 深度优先搜索


import java.util.ArrayList;
import java.util.List;

class SumNumbers {

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

        public int sumNumbers(TreeNode root) {
            if (root == null) {
                return 0;
            }
            List<Integer> list = new ArrayList<>();
            f(root, list, root.val + "");
            int sum = 0;
            for (Integer integer : list) {
                sum += integer;
            }
            return sum;
        }

        public void f(TreeNode root, List<Integer> list, String s) {
            if (root.left == null && root.right == null) {
                list.add(Integer.parseInt(s));
                return;
            }
            if (root.left != null) {
                f(root.left, list, s + root.left.val);
            }
            if (root.right != null) {
                f(root.right, list, s + root.right.val);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        SumNumbers sumNumbers = new SumNumbers();
        Solution solu = sumNumbers.new Solution();
        TreeNode t1 = sumNumbers.new TreeNode(1);
        TreeNode t2 = sumNumbers.new TreeNode(2);
        TreeNode t3 = sumNumbers.new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        System.out.println(solu.sumNumbers(t1));
    }
}