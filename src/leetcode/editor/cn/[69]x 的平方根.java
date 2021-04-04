//实现 int sqrt(int x) 函数。 
//
// 计算并返回 x 的平方根，其中 x 是非负整数。 
//
// 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。 
//
// 示例 1: 
//
// 输入: 4
//输出: 2
// 
//
// 示例 2: 
//
// 输入: 8
//输出: 2
//说明: 8 的平方根是 2.82842..., 
//     由于返回类型是整数，小数部分将被舍去。
// 
// Related Topics 数学 二分查找 
// 👍 640 👎 0

package leetcode.editor.cn;

/**
 * @date 2021-04-04 16:34:03
 * @auth cui
 */
class Sqrtx {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int mySqrt(int x) {
            long b = 1, e = x;
            for (long m = (b+e)/2; b <= e; m = (b+e)/2) {
                if (m*m > x){
                    if (e == m){
                        return (int) m;
                    }
                    e = m;
                } else if (m*m < x) {
                    if (b == m){
                        return (int) m;
                    }
                    b = m;
                } else if (m*m == x){
                    return (int) m;
                }
            }
            return 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new Sqrtx().new Solution();
        System.out.println(solution.mySqrt(2147395599));
    }
}