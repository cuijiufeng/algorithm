package leetcode.editor.cn;

/**
 * @ClassName: AlgoDay4_2
 * @Description:
 * @Date: 2020/6/12 14:06
 * @auth: Administrator
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 注意：给定 n 是一个正整数。
 * 示例 1：
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 */
public class AlgoDay4_2 {
    public static void main(String[] args){
        AlgoDay4_2 algo = new AlgoDay4_2();
        System.out.println(algo.climbStairs(45));
    }

    /**
     * @Desc: 如果第一步只上了一个台阶，则需要求解f(n-1)子问题
     *      如果第一步上了两个台阶，则需要求解f(n-2)子问题
     *      则问题的解f(n) = f(n-1)+f(n-2)
     * @Date: 2020/6/12 14:31
     * @param n
     * @return int
     * @throws
     */
    public int climbStairs1(int n) {
        if(n<3){
            return n;
        }
        return climbStairs(n-1)+climbStairs(n-2);
    }

    /**
     * @Desc: 其实此问题就是斐波那契数列问题
     * @Date: 2020/6/12 14:35
     * @param n
     * @return int
     * @throws
     */
    public int climbStairs(int n){
        if(n<3){
            return n;
        }
        int a = 1, b = 2, t=0;
        while((n--)!=0){
            t = a;
            a = b;
            b = b+t;
        }
        return t;
    }
}
