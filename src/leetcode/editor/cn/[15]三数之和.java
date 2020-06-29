package leetcode.editor.cn;
//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(solu.threeSum(new int[]{-5, 0, -2, 3, -2, 1, 1, 3, 0, -5, 3, 3, 0, -1}));
    }
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    if(nums[i]+nums[j]+nums[k] == 0){
                        List<Integer> t = new ArrayList<>();
                        t.add(nums[i]);
                        t.add(nums[j]);
                        t.add(nums[k]);
                        if(!Solution.containsRepeat(list, t)){
                            list.add(t);
                        }
                    }
                }
            }
        }
        return list;
    }

    public static boolean containsRepeat(List<List<Integer>> src, List<Integer> op){
        List<Integer> t = new ArrayList<>(op);
        for (int i = 0; i < src.size(); i++) {
            t = new ArrayList<>(op);
            for (int j = 0; j < src.get(i).size(); j++) {
                for (int k = 0; k < t.size(); k++) {
                    if(t.get(k).equals(src.get(i).get(j))){
                        t.remove(k);
                        break;
                    }
                }
            }
            if(t.size() == 0){
                return true;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
