package leetcode.editor.cn;
//给定一个 没有重复 数字的序列，返回其所有可能的全排列。
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法


import java.util.ArrayList;
import java.util.List;

class Permute {
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> list = new ArrayList<>();
            f(nums, 0, list, new ArrayList<>());
            return list;
        }

        public void f(int[] nums, int pos, List<List<Integer>> list, List<Integer> t) {
            if (pos >= nums.length) {
                list.add(new ArrayList<>(t));
            }
            for (int i = 0; i < nums.length; i++) {
                if (!t.contains(nums[i])) {
                    int size = t.size();
                    t.add(nums[i]);
                    f(nums, pos + 1, list, t);
                    t.remove(size);
                }
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)
    public static void main(String[] args) {
        Solution solu = new Permute().new Solution();
        List<List<Integer>> permute = solu.permute(new int[]{1, 2, 3});
        for (List<Integer> integers : permute) {
            System.out.println(integers.toString());
        }
    }

}

