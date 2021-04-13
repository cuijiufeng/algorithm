package leetcode.editor.cn;
//给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。 
//
// 
//
// 在杨辉三角中，每个数是它左上方和右上方的数的和。 
//
// 示例: 
//
// 输入: 3
//输出: [1,3,3,1]
// 
//
// 进阶： 
//
// 你可以优化你的算法到 O(k) 空间复杂度吗？ 
// Related Topics 数组


import java.util.ArrayList;
import java.util.List;

class GetRow {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<Integer> getRow(int rowIndex) {
            List<Integer> list = new ArrayList<>();
            list.add(1);
            if (rowIndex == 0) {
                return list;
            }
            list.add(1);
            if (rowIndex == 1) {
                return list;
            }
            for (int i = 2; i <= rowIndex; i++) {
                list.add(1);
                for (int j = i - 1; j > 0; j--) {
                    Integer num = list.get(j) + list.get(j - 1);
                    list.set(j, num);
                }
            }
            return list;
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solu = new GetRow().new Solution();
        System.out.println(solu.getRow(4));
    }
}