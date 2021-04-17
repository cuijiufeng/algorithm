//给定一个整数，将其转化为7进制，并以字符串形式输出。 
//
// 示例 1: 
//
// 
//输入: 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: -7
//输出: "-10"
// 
//
// 注意: 输入范围是 [-1e7, 1e7] 。 
// 👍 81 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

/**
 * @date 2021-04-17 14:09:07
 * @auth cui
 */
class Base7 {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String convertToBase7(int num) {
            if (num == 0){
                return "0";
            }
            boolean flag = (num < 0);
            num = Math.abs(num);
            List<Integer> list = new ArrayList<>();
            while (num != 0){
                list.add(num % 7);
                num /= 7;
            }
            StringBuilder sb = new StringBuilder();
            list.stream().forEach(i->sb.insert(0, i));
            return flag ? sb.insert(0, "-").toString() : sb.toString();
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    public static void main(String[] args) {
        Solution solution = new Base7().new Solution();
        System.out.println(solution.convertToBase7(-8));
    }
}