package leetcode.editor.cn;
//给定一个二叉树，判断其是否是一个有效的二叉搜索树。
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class IsValidBST {
    public static void main(String[] args) {
        IsValidBST isValidBST = new IsValidBST();
        Solution solu = isValidBST.new Solution();
        TreeNode t1 = isValidBST.new TreeNode(10);
        TreeNode t2 = isValidBST.new TreeNode(5);
        TreeNode t3 = isValidBST.new TreeNode(15);
        TreeNode t4 = isValidBST.new TreeNode(6);
        TreeNode t5 = isValidBST.new TreeNode(20);
        t1.left = t2;
        t1.right = t3;
        t3.left = t4;
        t3.right = t5;
        System.out.println(solu.isValidBST(t1));
    }

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


        //如果中序遍历是升序的，则是搜索二叉树
        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            //中序非递归遍历
            Stack<TreeNode> stack = new Stack<>();
            List<TreeNode> traverse = new ArrayList<>();
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                traverse.add(pop);
                if (pop.right != null) {
                    TreeNode t = pop.right;
                    while (t != null) {
                        stack.push(t);
                        t = t.left;
                    }
                }
            }
            for (int i = 1; i < traverse.size(); i++) {
                if (traverse.get(i - 1).val >= traverse.get(i).val) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}

