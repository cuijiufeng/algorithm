package leetcode.editor.cn;
//给定一个由整数组成的非空数组所表示的非负整数，在该数的基础上加一。
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 示例 1: 
//
// 输入: [1,2,3]
//输出: [1,2,4]
//解释: 输入数组表示数字 123。
// 
//
// 示例 2: 
//
// 输入: [4,3,2,1]
//输出: [4,3,2,2]
//解释: 输入数组表示数字 4321。
// 
// Related Topics 数组


import java.util.Arrays;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(Arrays.toString(solu.plusOne(new int[]{9})));
    }
    public int[] plusOne(int[] digits) {
        digits[digits.length-1] += 1;
        for(int i=digits.length-2; i>=0; i--){
            if(digits[i+1]/10 == 1){
                digits[i+1] %= 10;
                digits[i] += 1;
            }
        }
        if(digits[0]>9){
            int[] src = new int[digits.length+1];
            System.arraycopy(digits, 0, src, 1, digits.length);
            digits = src;
            digits[1] %= 10;
            digits[0] = 1;
        }
        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
