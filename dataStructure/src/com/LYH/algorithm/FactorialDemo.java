package com.LYH.algorithm;

public class FactorialDemo {
    public static void main(String[] args) {
        GetFactorial factorial=new GetFactorial();
        System.out.println(factorial.Factorial(7));
    }
}
class GetFactorial{
//    接受一个参数n表示求谁的阶乘
    public int Factorial(int n){
//        当n等于1时，阶乘的结果就是1
        if (n==1){
            return 1;
        }else {   //当不为1，则进入递归
//            每次递归，调用自己的时候，传入的参数是(n-1)，参数总会比当前的n小1
            return n*Factorial(n-1);
        }
    }
}
