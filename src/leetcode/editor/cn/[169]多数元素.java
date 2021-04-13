package leetcode.editor.cn;
//给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
//
// 你可以假设数组是非空的，并且给定的数组总是存在多数元素。 
//
// 
//
// 示例 1: 
//
// 输入: [3,2,3]
//输出: 3 
//
// 示例 2: 
//
// 输入: [2,2,1,1,1,2,2]
//输出: 2
// 
// Related Topics 位运算 数组 分治算法

class MajorityElement {
    public static void main(String[] args) {
        Solution solu = new MajorityElement().new Solution();
        System.out.println(solu.majorityElement(new int[]{3, 2, 3}));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int majorityElement(int[] nums) {
            //票项
            int ballot = nums[0];
            //票数
            int ballotCount = 1;
            //投票法，如果相同票数加一，如果不同票数减一(票数为0时，切换票项并且初始化票数)
            for (int i = 1; i < nums.length; i++) {
                //如果相同，则票数加一
                if (nums[i] == ballot) {
                    ballotCount++;
                } else {
                    //如果不同，则票数减一
                    ballotCount--;
                    if (ballotCount == 0) {
                        ballot = nums[i];
                        ballotCount = 1;
                    }
                }
            }
            return ballot;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
