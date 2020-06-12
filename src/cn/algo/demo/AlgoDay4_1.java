package cn.algo.demo;

/**
 * @ClassName: AlgoDay4_1
 * @Description:
 * @Date: 2020/6/12 11:28
 * @auth: Administrator
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 */
public class AlgoDay4_1 {
    public static void main(String[] args){
        AlgoDay4_1 algo = new AlgoDay4_1();
        int a[] = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(algo.maxSubArray(a));
    }

    // 动态规划求解问题
    public int maxSubArray(int[] nums) {
        //最终的返回值
        int rs = Integer.MIN_VALUE;
        //dp[i+1]中存放着子问题nums[0]...nums[i]的连续子数组最大和是多少，至少包括nums[i]项
        int dp[] = new int[nums.length+1];
        for(int i=1; i<=nums.length; i++){
            //动态规划问题求解
            dp[i] = Math.max(dp[i-1], 0) + nums[i-1];
            //dp数组中最的最大值为问题的解
            if(dp[i]>rs){
                rs = dp[i];
            }
        }
        return rs;
    }
}
