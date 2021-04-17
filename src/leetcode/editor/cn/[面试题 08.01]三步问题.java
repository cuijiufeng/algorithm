//三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模100
//0000007。 
//
// 示例1: 
//
// 
// 输入：n = 3 
// 输出：4
// 说明: 有四种走法
// 
//
// 示例2: 
//
// 
// 输入：n = 5
// 输出：13
// 
//
// 提示: 
//
// 
// n范围在[1, 1000000]之间 
// 
// Related Topics 动态规划 
// 👍 51 👎 0

package leetcode.editor.cn;

/**
 * @date 2021-04-16 19:38:23
 * @auth cui
 */
class ThreeStepsProblemLcci {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int waysToStep(int n) {
            if (n == 1){
                return 1;
            } else if (n == 2){
                return 2;
            } else if (n == 3){
                return 4;
            }
            int dp[] = new int[1000001];
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;
            for (int i = 4; i <= n; i++) {
                dp[i] = ((dp[i-1] + dp[i-2]) % 1000000007 + dp[i-3]) % 1000000007;
            }
            return dp[n] % 1000000007;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new ThreeStepsProblemLcci().new Solution();
        System.out.println(solution.waysToStep(61));
    }
}