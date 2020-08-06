package leetcode.editor.cn;//给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
//
// 
// 二叉树的根是数组中的最大元素。 
// 左子树是通过数组中最大值左边部分构造出的最大二叉树。 
// 右子树是通过数组中最大值右边部分构造出的最大二叉树。 
// 
//
// 通过给定的数组构建最大二叉树，并且输出这个树的根节点。 
//
// 
//
// 示例 ： 
//
// 输入：[3,2,1,6,0,5]
//输出：返回下面这棵树的根节点：
//
//      6
//    /   \
//   3     5
//    \    / 
//     2  0   
//       \
//        1
// 
//
// 
//
// 提示： 
//
// 
// 给定的数组的大小在 [1, 1000] 之间。 
// 
// Related Topics 树 
// 👍 173 👎 0


import java.util.Arrays;

//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        solu.constructMaximumBinaryTree(new int[]{3,2,1,6,0,5});
    }
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0){
            return null;
        }
        TreeNode root;
        int pos = 0;
        for (int i = 1; i < nums.length; i++) {
            if(nums[i] > nums[pos]){
                pos = i;
            }
        }
        root = new TreeNode(nums[pos]);
        int[] left = Arrays.copyOfRange(nums, 0, pos);
        root.left = constructMaximumBinaryTree(left);
        int[] right = Arrays.copyOfRange(nums, pos+1, nums.length);
        root.right = constructMaximumBinaryTree(right);
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
