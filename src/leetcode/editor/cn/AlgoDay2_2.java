package leetcode.editor.cn;

import java.util.Arrays;

/**
 * @ClassName: AlgoDay2_2
 * @Description: day2_2
 * @Date: 2020/6/10 14:04
 * @auth: Administrator
 * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 */
public class AlgoDay2_2 {
    public static void main(String[] args){
        int[] nums= new int[]{-1,-2,-3,-4,-5};
        AlgoDay2_2 algoDay2_2 = new AlgoDay2_2();
        System.out.println(Arrays.toString(algoDay2_2.twoSum(nums, -8)));
    }

    public int[] twoSum(int[] nums, int target) {
        for (int i=0; i<nums.length; i++){
            for (int j=i+1; j<nums.length; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }
}
