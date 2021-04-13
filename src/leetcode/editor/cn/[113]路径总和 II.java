package leetcode.editor.cn;//给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//给定如下二叉树，以及目标和 sum = 22， 
//
//               5
//             / \
//            4   8
//           /   / \
//          11  13  4
//         /  \    / \
//        7    2  5   1
// 
//
// 返回: 
//
// [
//   [5,4,11,2],
//   [5,8,4,5]
//]
// 
// Related Topics 树 深度优先搜索


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class pathSum {

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


        public List<List<Integer>> pathSum(TreeNode root, int sum) {
            List<List<Integer>> lists = new ArrayList<>();
            if (root == null) {
                return lists;
            }
            f(root, sum, lists, new ArrayList<>());
            return lists;
        }

        public void f(TreeNode root, int sum, List<List<Integer>> list, List<TreeNode> path) {
            if (root.left == null && root.right == null) {
                if (sum == root.val) {
                    List<Integer> collect = path.stream().map(treeNode -> treeNode.val).collect(Collectors.toList());
                    collect.add(root.val);
                    list.add(collect);
                }
                return;
            }
            int size = path.size();
            if (root.left != null) {
                path.add(root);
                f(root.left, sum - root.val, list, path);
                path.remove(size);
            }
            if (root.right != null) {
                path.add(root);
                f(root.right, sum - root.val, list, path);
                path.remove(size);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solu = new pathSum().new Solution();
        TreeNode t1 = new pathSum().new TreeNode(5);
        TreeNode t2 = new pathSum().new TreeNode(4);
        TreeNode t3 = new pathSum().new TreeNode(8);
        TreeNode t4 = new pathSum().new TreeNode(11);
        TreeNode t5 = new pathSum().new TreeNode(13);
        TreeNode t6 = new pathSum().new TreeNode(4);
        TreeNode t7 = new pathSum().new TreeNode(7);
        TreeNode t8 = new pathSum().new TreeNode(2);
        TreeNode t9 = new pathSum().new TreeNode(5);
        TreeNode t10 = new pathSum(). new TreeNode(1);
        t1.left = t2;
        t1.right = t3;
        t2.left = t4;
        t4.left = t7;
        t4.right = t8;
        t3.left = t5;
        t3.right = t6;
        t6.left = t9;
        t6.right = t10;

        TreeNode tt1 = new pathSum().new TreeNode(1);
        TreeNode tt2 = new pathSum().new TreeNode(2);
        tt1.left = tt2;

        List<List<Integer>> list = solu.pathSum(tt1, 1);
        for (List<Integer> integers : list) {
            System.out.println(integers.toString());
        }
    }
}