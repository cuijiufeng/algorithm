//给定一个非负整数，你至多可以交换一次数字中的任意两位。返回你能得到的最大值。 
//
// 示例 1 : 
//
// 
//输入: 2736
//输出: 7236
//解释: 交换数字2和数字7。
// 
//
// 示例 2 : 
//
// 
//输入: 9973
//输出: 9973
//解释: 不需要交换。
// 
//
// 注意: 
//
// 
// 给定数字的范围是 [0, 108] 
// 
// Related Topics 数组 数学 
// 👍 99 👎 0


//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (maxInt(chars, i)){
                break;
            }
        }
        return Integer.parseInt(new String(chars));
    }

    public boolean maxInt(char[] chars, int pos){
        int max = pos;
        for (int i = pos+1; i < chars.length; i++) {
            if (chars[i] > chars[max]){
                max = i;
            } else if (chars[i] == chars[max]){
                char t = chars[max];
                chars[max] = chars[pos];
                chars[pos] = t;
                int i1 = Integer.parseInt(new String(chars));
                t = chars[max];
                chars[max] = chars[pos];
                chars[pos] = t;

                t = chars[i];
                chars[i] = chars[pos];
                chars[pos] = t;
                int i2 = Integer.parseInt(new String(chars));
                t = chars[i];
                chars[i] = chars[pos];
                chars[pos] = t;
                if(i2>i1){
                    max = i;
                }
            }
        }
        char t = chars[max];
        chars[max] = chars[pos];
        chars[pos] = t;
        return max != pos;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
