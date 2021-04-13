package leetcode.editor.cn;//给定一个正整数 n，你可以做如下操作：
//
// 1. 如果 n 是偶数，则用 n / 2替换 n。 
//2. 如果 n 是奇数，则可以用 n + 1或n - 1替换 n。 
//n 变为 1 所需的最小替换次数是多少？ 
//
// 示例 1: 
//
// 
//输入:
//8
//
//输出:
//3
//
//解释:
//8 -> 4 -> 2 -> 1
// 
//
// 示例 2: 
//
// 
//输入:
//7
//
//输出:
//4
//
//解释:
//7 -> 8 -> 4 -> 2 -> 1
//或
//7 -> 6 -> 3 -> 2 -> 1
// 
// Related Topics 位运算 数学 
// 👍 70 👎 0


class IntegerReplacement{
    public static void main(String[] args){
        Solution solu = new IntegerReplacement().new Solution();
        System.out.println(solu.integerReplacement(1234));
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        //使用递归时栈内存溢出
        public int integerReplacement(int n) {
            if (n == 1){
                return 0;
            }
            if ((n & 0x1) == 0){
                return integerReplacement(n >> 1) + 1;
            } else {
                return Math.min(integerReplacement(n + 1), integerReplacement(n - 1)) + 1;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}