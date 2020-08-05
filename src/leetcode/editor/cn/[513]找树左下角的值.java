package leetcode.editor.cn;
//给定一个二叉树，在树的最后一行找到最左边的值。 
//
// 示例 1: 
//
// 
//输入:
//
//    2
//   / \
//  1   3
//
//输出:
//1
// 
//
// 
//
// 示例 2: 
//
// 
//输入:
//
//        1
//       / \
//      2   3
//     /   / \
//    4   5   6
//       /
//      7
//
//输出:
//7
// 
//3,1,5,0,2,4,6,null,null,null,3
// 
//
// 注意: 您可以假设树（即给定的根节点）不为 NULL。 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 102 👎 0


import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        List<TreeNode> t = null;
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        while (!queue.isEmpty()){
            t = new ArrayList<>(queue);
            queue.clear();
            for (TreeNode treeNode : t) {
                if (treeNode.left != null){
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null){
                    queue.add(treeNode.right);
                }
            }
        }
        return t.get(0).val;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
