//给定一个二叉树，原地将它展开为一个单链表。 
//
// 
//
// 例如，给定二叉树 
//
//     1
//   / \
//  2   5
// / \   \
//3   4   6 
//
// 将其展开为： 
//
// 1
// \
//  2
//   \
//    3
//     \
//      4
//       \
//        5
//         \
//          6 
// Related Topics 树 深度优先搜索 
// 👍 515 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//leetcode submit region begin(Prohibit modification and deletion)
//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        solu.flatten(null);
    }
    public void flatten(TreeNode root) {
        if (root == null){
            return;
        }
        TreeNode t = root;
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        while(t != null){
            list.add(t);
            stack.push(t);
            t = t.left;
        }
        while (!stack.isEmpty()){
            TreeNode pop = stack.pop();
            if ((t = pop.right) != null){
                while (t != null){
                    list.add(t);
                    stack.push(t);
                    t = t.left;
                }
            }
        }
        t = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            t.left = null;
            t.right = list.get(i);
            t = list.get(i);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
