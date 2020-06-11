package cn.algo.demo;

/**
 * @ClassName: AlgoDay3_2
 * @Description:
 * @Date: 2020/6/11 13:20
 * @auth: Administrator
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 * 你可以假设 nums1 和 nums2 不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class AlgoDay3_2 {
    
    public static void main(String[] args){
        AlgoDay3_2 algo = new AlgoDay3_2();
        System.out.println(algo.findMedianSortedArrays(new int[]{1, 2}, new int[]{3, 4}));
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //先把两个有序的数组合并成一个有序的数组
        int[] nums = new int[nums1.length+nums2.length];
        int i = 0, j=0, cnt = 0;
        //全并过程
        while(i<nums1.length && j< nums2.length){
            if(nums1[i]<nums2[j]){
                nums[cnt++] = nums1[i++];
            } else {
                nums[cnt++] = nums2[j++];
            }
        }
        //如果一个数组还有剩余的元素，则依次放入
        while(i<nums1.length){
            nums[cnt++] = nums1[i++];
        }
        while(j<nums2.length){
            nums[cnt++] = nums2[j++];
        }
        i = 0;
        j = nums.length - 1;
        //循环，去掉一个最大的，去掉一个最小的
        while(i<j){
            i++;
            j--;
        }
        //当j,j碰在一起，就是中位数的位置
        if(i == j){
            return nums[i];
        } else {
            return (nums[i]+nums[j])/2.0;
        }
    }
}
