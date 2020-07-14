package leetcode.editor.cn;//给定一个二叉树，返回它的 前序 遍历。
//
// 示例: 
//
// 输入: [1,null,2,3]  
//   1
//    \
//     2
//    /
//   3 
//
//输出: [1,2,3]
// 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> list = new ArrayList<>();
        while (root != null){
            list.add(root);
            stack.push(root);
            root = root.left;
        }
        while (!stack.isEmpty()){
            TreeNode t = stack.pop();
            if (t != null){
                t = t.right;
                while (t != null){
                    list.add(t);
                    stack.push(t);
                    t = t.left;
                }
            }
        }
        return list.stream().map(tn->tn.val).collect(Collectors.toList());
    }
}
//leetcode submit region end(Prohibit modification and deletion)
