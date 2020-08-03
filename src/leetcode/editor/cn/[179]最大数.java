package leetcode.editor.cn;
//给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
//
// 示例 1: 
//
// 输入: [10,2]
//输出: 210 
//
// 示例 2: 
//
// 输入: [3,30,34,5,9]
//输出: 9534330 
//
// 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。 
// Related Topics 排序
import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(solu.largestNumber(new int[]{0,0}));
    }
    public String largestNumber(int[] nums) {
        String[] strings = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strings[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                if (s1.equals(s2)){
                    return -1;
                }
                for (int i = 0; i < s1.length() && i < s2.length(); i++) {
                    if (s1.charAt(i) > s2.charAt(i)){
                        return -1;
                    } else if (s1.charAt(i) < s2.charAt(i)){
                        return 1;
                    }
                }
                return compare(s1+s2, s2+s1);
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strings.length; i++) {
                sb.append(strings[i]);
        }
        if (sb.toString().matches("[0]+")){
            return "0";
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)
