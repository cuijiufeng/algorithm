package cn.algo.demo;

import java.util.Scanner;

/**
 * @ClassName: AlgoDay2
 * @Description: day2
 * @Date: 2020/6/10 13:53
 * @auth: Administrator
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 */
public class AlgoDay2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        AlgoDay2 algoDay2 = new AlgoDay2();
        System.out.println(algoDay2.isPalindrome(num));
    }

    public boolean isPalindrome(int x) {
        if(x<0){
            return false;
        }
        int num = 0;
        int t = x;
        while(t!=0){
            num = num*10+t%10;
            t /= 10;
        }
        return num == x;
    }
}
