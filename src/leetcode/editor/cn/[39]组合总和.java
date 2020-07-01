package leetcode.editor.cn;
//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [2,3,6,7], target = 7,
//所求解集为:
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,3,5], target = 8,
//所求解集为:
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
// Related Topics 数组 回溯算法


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public static void main(String[] args){
        Solution solu = new Solution();
        System.out.println(solu.combinationSum(new int[]{2, 3, 6, 7}, 7));
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> list = new ArrayList<>();
        resolveQuestin(list, candidates, target, new ArrayList<>());
        for (int i = 0; i < list.size(); i++) {
            List<Integer> integers = list.get(i);
            for (int j = 0; j < list.size(); j++) {
                if(i != j && integers.equals(list.get(j))){
                    list.remove(i);
                    i--;
                    break;
                }
            }
        }
        return list;
    }

    public void resolveQuestin(List<List<Integer>> list, int[] candidates, int target, List<Integer> data){
        if(target<0){
            return;
        } else if (0 == target){
            List<Integer> integers = new ArrayList<>(data);
            Collections.sort(integers);
            list.add(integers);
        }
        for (int i=0; i<candidates.length; i++) {
            data.add(candidates[i]);
            resolveQuestin(list, candidates, target-candidates[i], data);
            data.remove(data.size()-1);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
