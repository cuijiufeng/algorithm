//给定一个二叉树，返回它的 后序 遍历。 
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
//输出: [3,2,1] 
//
// 进阶: 递归算法很简单，你可以通过迭代算法完成吗？ 
// Related Topics 栈 树 深度优先搜索 二叉树 
// 👍 694 👎 0


package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class BinaryTreePostorderTraversal {
public static class TreeNode {
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

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //此方法会破坏掉数结构
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> rs = new ArrayList<>(16);
        TreeNode t = root;
        while (t != null) {
            stack.push(t);
            t = t.left;
        }
        while (!stack.isEmpty()) {
            //查看是否有右子树需要遍历
            TreeNode peek = stack.peek();
            t = peek.right;
            //标记此结点右子树已经被遍历过了
            peek.right = null;
            //如果此结点没有右子树，而左子树此时一定是被访问过了的。所以当前访问此结点
            if (t == null) {
                rs.add(stack.pop().val);
                continue;
            }
            while (t != null) {
                stack.push(t);
                t = t.left;
            }
        }
        return rs;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new BinaryTreePostorderTraversal().new Solution();
        System.out.println(solution.postorderTraversal(new TreeNode(1, null, new TreeNode(2, new TreeNode(3, null, null), null))));
    }
}