package leetcode.editor.cn;
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        List<List<Integer>> lists = solu.combinationSum2(new int[]{2,5,2,1,2}, 5);
        for (List<Integer> list : lists) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        f(candidates, target, new ArrayList<Integer>(), 0, list);
        return list;
    }

    public void f(int[] cadidates, int target, List<Integer> pos, int sum, List<List<Integer>> list){
        if(sum > target){
            return;
        }
        if(sum == target){
            List<Integer> t = pos.stream().map(i -> cadidates[i]).collect(Collectors.toList());
            t.sort(null);
            if (!list.contains(t)){
                list.add(t);
            }
            return;
        }
        for (int i = 0; i < cadidates.length && !pos.contains(i); i++) {
            int size = pos.size();
            pos.add(i);
            f(cadidates, target, pos, sum+cadidates[i], list);
            pos.remove(size);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
