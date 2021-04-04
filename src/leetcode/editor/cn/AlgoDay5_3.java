package leetcode.editor.cn;

/**
 * @ClassName: AlgoDay5_3
 * @Description:
 * @Date: 2020/6/15 11:20
 * @auth: Administrator
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 */
public class AlgoDay5_3 {
    public String longestCommonPrefix(String[] strs) {
        String str = "";
        int len = 0;
        if (strs.length==0){
            return str;
        }
        while (true){
            for(int i=0; i<strs.length; i++){
                if(len>=strs[i].length()){
                    return str;
                }
                if(strs[i].charAt(len)!=strs[0].charAt(len)){
                    return str;
                }
            }
            str += strs[0].charAt(len);
            len++;
        }
    }
    public static void main(String[] args){
        AlgoDay5_3 algo = new AlgoDay5_3();
        String[] strs = new String[]{"a"};
        System.out.println(algo.longestCommonPrefix(strs));
    }
}
