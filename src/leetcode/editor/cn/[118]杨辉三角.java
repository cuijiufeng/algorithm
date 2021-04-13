package leetcode.editor.cn;//给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 5
//输出:
//[
//     [1],
//    [1,1],
//   [1,2,1],
//  [1,3,3,1],
// [1,4,6,4,1]
//] 
// Related Topics 数组


import java.util.ArrayList;
import java.util.List;

class Generate {

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<Integer>> generate(int numRows) {
            List<List<Integer>> list = new ArrayList<>();
            for (int i = 0; i < numRows; i++) {
                List<Integer> l = new ArrayList<>(i + 1);
                for (int j = 0; j < i + 1; j++) {
                    if (j == 0 || j == i) {
                        l.add(1);
                    } else {
                        Integer num = list.get(i - 1).get(j) + list.get(i - 1).get(j - 1);
                        l.add(num);
                    }
                }
                list.add(l);
            }
            return list;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solution = new Generate().new Solution();
    }
}