//给定一个非负整数数组 nums ，你最初位于数组的 第一个下标 。 
//
// 数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//            2  4 3 4
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 3 * 104 
// 0 <= nums[i] <= 105 
// 
// Related Topics 贪心 数组 动态规划 
// 👍 1425 👎 0


package leetcode.editor.cn;

import java.util.Arrays;

class JumpGame {
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    //这不是一种最优的解决方法，
    //使用递归的方式，空间复杂度上升，从O(1)大约上升到了O(n)
    //他并不能得到一个最优的跳跃方式，但是可以得到跳跃可不可行
    public boolean canJump(int[] nums) {
        return recursionCanJump(nums, nums.length);
    }
    //拆解问题，
    //从那些位置可以跳跃到终点，便可以取此点为子问题的终点，以此循环来解决整个问题
    public boolean recursionCanJump(int [] nums, int length) {
        //如果当前问题只有一个点，即终点，认为问题有解的
        if (length <= 1) {
            return true;
        }
        int target = length - 1;
        for (int i = target - 1; i >= 0; i--) {
            //如果此位置可以到达当前问题的终点，则以此位置作为终点去求解子问题的解
            if (i + nums[i] >= target) {
                return recursionCanJump(nums, i + 1);
            }
        }
        //如果任意一个位置都不能到达此问题的终点，认为此问题是无解的
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new JumpGame().new Solution();
        System.out.println(solution.canJump(new int[]{2,3,1,1,4}));
    }
}