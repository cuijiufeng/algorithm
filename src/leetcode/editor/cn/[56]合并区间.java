//给出一个区间的集合，请合并所有重叠的区间。 
//
// 
//
// 示例 1: 
//
// 输入: intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: intervals = [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 注意：输入类型已于2019年4月15日更改。 请重置默认代码定义以获取新方法签名。 
//
// 
//
// 提示： 
//
// 
// intervals[i][0] <= intervals[i][1] 
// 
// Related Topics 排序 数组 
// 👍 562 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i-1][1] >= intervals[i][0]){
                list.add(i-1);
                if (intervals[i-1][0] < intervals[i][0]){
                    intervals[i][0] = intervals[i-1][0];
                }
                if (intervals[i-1][1] > intervals[i][1]){
                    intervals[i][1] = intervals[i-1][1];
                }
            }
        }
        List<int[]> rs = new ArrayList<>();
        for (int i = 0; i < intervals.length; i++) {
            if (!list.contains(i)){
                rs.add(intervals[i]);
            }
        }
        Object[] objects = rs.toArray();
        int[][] ints = Arrays.copyOf(objects, objects.length, int[][].class);
        return ints;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
