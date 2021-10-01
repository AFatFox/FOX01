package com.LYH.structure.Linear;

import java.util.Scanner;

public class CircularQueue {
    public static void main(String[] args) {
        CircularQueueDemo queue = new CircularQueueDemo(3);
        Scanner scanner = new Scanner(System.in);
        boolean flag=true;
        while (flag){
            System.out.println("存数据：1");
            System.out.println("取数据：2");
            System.out.println("显示队列所有数据：3");
            System.out.println("显示头数据：4");
            System.out.println("退出：5");
            char n=scanner.next().charAt(0);   //CharAt(0)可以得到字符串的第一个字符
            switch (n) {
                case '1':   //单引号表示单个字符
                    System.out.println("输入数据");
                    queue.addQueue(scanner.nextInt());
                    break;
                case '2':
                    try {
                        int i=queue.getQueue();
                        System.out.println(i);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case '3':
                    queue.showQueue();
                    break;
                case '4':
                    try {
                        int i=queue.showHead();
                        System.out.println(i);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case '5':
                    flag=false;
                    break;
                default:
                    break;
            }
        }
    }
}
class CircularQueueDemo{
    private int maxSize;  //队列最大容量
    private int front;  //对队列头
    private int rear;  //队列尾
    private int[] arr;  //此数组用于存放数据，模拟队列

    public CircularQueueDemo(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = 0;
        this.rear = 0;
    }

//    判断队列是否为空
    public boolean isEmpty(){
        return front==rear;  //true为满  false为非满
    }

//    判断队列是否已满
    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

//    得到队列中总共有几个数据。用于打印输出
    public int number(){
        return (rear-front+maxSize)%maxSize;
    }
//    增加数据
    public void addQueue(int n){
    //        判断队列是否已满
        if(isFull()){
            System.out.println("队列已满，无法添加");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }
//    取出数据
    public int getQueue(){
        if (isEmpty()){
    /*      因为此函数有一个返回值，当我们无法得到数据的时候，就不需要进行返回数值
            所以不能用  return 值   的形式进行函数退出，
            也不能直接return退出，因为这里需要一个返回值，用return的话就必须给他加值
            所以，我们可以用异常抛出的方法退出这个函数，并抛出异常
     */
            throw new RuntimeException("队列为空，无法取数据");
        }
        int n = arr[front];
        front=(front+1)%maxSize;
        return n;
    }
//    显示队列的数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("无数据");
            return;
        }
        for (int i = front; i < front+number(); i++) {
            System.out.println("arr["+(i%maxSize)+"]的值是："+arr[(i%maxSize)]);
        }
    }
//    显示头数据
    public int showHead(){
        if (isEmpty()){
            throw new RuntimeException("无数据");
        }
        return arr[front];
    }
}