package leetcode.editor.cn;
//给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
//
// 说明： 
//
// 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？ 
//
// 示例 1: 
//
// 输入: [2,2,1]
//输出: 1
// 
//
// 示例 2: 
//
// 输入: [4,1,2,1,2]
//输出: 4 
// Related Topics 位运算 哈希表


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int singleNumber1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            map.put(nums[i], map.get(nums[i])==null?1:map.get(nums[i])+1);
        }
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            if (integerIntegerEntry.getValue()==1) {
                return integerIntegerEntry.getKey();
            }
        }
        return 0;
    }

    /**
     * @Desc: 所有的数字都出现过两次，只有一个数字出现过一次。a^b^b=a,a^b^c^b^c=a;所以可以如下解法
     * @Date: 2020/6/22 10:06
     * @param nums
     * @return int
     * @throws
     */
    public int singleNumber(int[] nums) {
        int num = nums[0];
        for (int i=1; i<nums.length; i++){
            num = num^nums[i];
        }
        return num;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
