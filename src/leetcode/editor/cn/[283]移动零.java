//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 1019 👎 0

package leetcode.editor.cn;

import java.util.Arrays;

/**
 * @date 2021-04-08 20:53:27
 * @auth cui
 */
class MoveZeroes {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public void moveZeroes(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] != 0) {
                    for (int j = i; j > 0 && nums[j - 1] == 0; j--) {
                        nums[j - 1] = nums[j];
                        nums[j] = 0;
                    }
                }
            }
            System.out.println(Arrays.toString(nums));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new MoveZeroes().new Solution();
        solution.moveZeroes(new int[]{0, 1, 0, 3, 12});
    }
}