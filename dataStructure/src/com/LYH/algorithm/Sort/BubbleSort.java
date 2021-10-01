package com.LYH.algorithm.Sort;


public class BubbleSort {
    public static void main(String[] args) {
        BubbleDemo bubble=new BubbleDemo();

        int[] array=new int[80000];
        for (int i = 0; i < 80000; i++) {
            array[i]= (int) (Math.random()*80000);  //Math.random可以获得一个0到1之间的随机浮点数
        }
        Long time01=System.currentTimeMillis();
//        该方法的作用是返回当前的计算机时间，时间的表达格式为当前计算机时间和GMT时间(格林威治时间)1970年1月1号0时0分0秒所差的毫秒数。

        bubble.Bubble(array);
        Long time02=System.currentTimeMillis();
        System.out.println("共消耗了"+(time02-time01)+"毫秒");
    }
}
class BubbleDemo{
    public int[] Bubble(int[] array){
        int temp=0;
        boolean flag=false;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j]>array[j+1]){
                    flag=true;
                    temp=array[j];
                    array[j]=array[j+1];
                    array[j+1]=temp;
                }
            }
            if (!flag){
                break;
            }else {
                flag=false;
            }
        }

        return array;
    }
}
