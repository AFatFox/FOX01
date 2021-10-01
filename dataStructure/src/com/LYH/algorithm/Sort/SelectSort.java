package com.LYH.algorithm.Sort;

import java.util.Arrays;

public class SelectSort {
    public static void main(String[] args) {
        SelectSortDemo SortDemo=new SelectSortDemo();
        int array[]=new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i]=(int)Math.random()*80000;
        }
        Long time1=System.currentTimeMillis();
        SortDemo.Sort(array);
        Long time2=System.currentTimeMillis();
        System.out.println("耗时"+(time2-time1));

//        System.nanoTime()返回的是纳秒，nanoTime而返回的可能是任意时间，甚至可能是负数。
//        每个JVM维护一份，和系统时间无关，可用于计算时间间隔，比System.currentTimeMillis的精度要高。
//        修改了系统时间会对System.currentTimeMillis造成影响，而对System.nanoTime没有影响
//        该函数只能用于计算时间差，不能用于计算距离现在的时间。
    }
}
class SelectSortDemo{
    public int[] Sort(int[] array){
//        选择排序：时间复杂度：O(n^2)
//        原理：每次找到数列中的最值，并将其放在数列头部
//        大致原理：每次找到最值并将其放在数列头部后，就固定此值的位置，后面数列中的最值放在这些固定值的后面，当数列中仅剩一个值未固定，则表示排序结束
//        进一步的原理：假设首先找到了数列中最小的值，则将此最小值和数列头的数据进行交换，此时则表示完成第一次排序，
//        然后进入下一次排序，在下一次找最小值的过程中，去除掉数列头的位置(即上次寻找到的最小值)，
//        在剩余的数列中寻找最小值，找到后重复上面的操作，此处便是将这个最小值放在所有数列中排第二的位置

//        实现原理：
//        首先肯定是需要两层for循环嵌套。外层的for循环用于控制次数，即需要寻找几次最值。
//        当所有的数列中的值只剩下一个没被固定，则表示排序完成，所以此处是0~array.length-1。即数列中数值的数量-1
        for (int i = 0; i <array.length-1 ; i++) {

//            每次的寻找最小值都需要先拥有一个值，我们此处得到第一个值当做初始的值，
//            简单来说，就是在还未找到最小值的那部分数列中，首先取其第一个值，用于二层循环中的第一次比较
            int n=array[i];   //得到第一个值
            int m=i;    //得到第一个值对应的索引，用于后续的交换

//            二层for循环，这个for循环用于控制比较和交换，j同时用于作为数组的索引
//            首先，第一个值我们已经有了，索引我们要跳过第一个值，所以j从i+1开始，
//            这里是需要比较的，即我们需要验证数列中的所有数值，所以此处索引应该到达array.length-1，所以此处是< array.length
            for (int j = 1+i; j < array.length; j++) {

//                进行比较，如果第一个值不是最值
                if (n>array[j]){
//                    我们则抛弃第一个值，来使用此时找到的最值。(后续的for循环中会持续找最值并更新最值，知道for结束)
                    n=array[j];
//                    并更新对应的索引
                    m=j;
                }
            }

//            上面而层for循环结束，则表示找到了一个最值，若此最值不在数列头部，则将其和数列头部数据进行交换，将其放在当前所属数列的头部，
            if (m!=i) {
                array[m] = array[i];
                array[i] = n;
            }
        }
        return array;
    }
}
