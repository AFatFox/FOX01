package com.LYH.structure.Linear;

import java.util.Scanner;

public class ArrayStackDemo {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String choice;
        ArrayStack stack=null;
        while (true){
            System.out.println("创建栈，1。退出，0。");
            boolean flag=false;
            choice=sc.next();
            if (choice.equals("1")){
                String Size;
                int ISize;
                while (true){
                    System.out.println("输入大小");
                    Size=sc.next();
                    try {
                        ISize=Integer.parseInt(Size);

                        break;
                    }catch (Exception e){
                        System.out.println("输入错误");
                    }
                }
                stack=new ArrayStack(ISize);
                flag=true;
            }else if(choice.equals("0")){
                break;
            }
            if (flag){
                while (true) {
                    System.out.println("入栈，1。出栈，2。全部显示，3。返回,0");
                    String choice2;
                    choice2=sc.next();
                    if (choice2.equals("1")) {
                        String Value;
                        int IValue;
                        while (true) {
                            System.out.println("输入数值");
                            Value = sc.next();
                            try {
                                IValue = Integer.parseInt(Value);
                                break;
                            } catch (Exception e) {
                                System.out.println("输入错误");
                            }
                        }
                        stack.push(IValue);
                    }else if (choice2.equals("2")){
                        try {
                            System.out.println(stack.pop());
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }

                    }else if (choice2.equals("3")){
                        stack.show();
                    }else if (choice2.equals("0")){
                        break;
                    }
                }
            }
        }
    }
}
class ArrayStack{
    private int top=-1; //栈顶，初始化为-1
    private int maxSize;  //栈最大容量
    private int[] Stack;  //栈底层用数组实现

//    构造方法，对栈初始化
    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        this.Stack = new int[this.maxSize];
    }

//    判断是否栈满
    public Boolean isFull(){
        return top==maxSize-1;
    }

//    判断栈空
    public Boolean isEmpty(){
        return top==-1;
    }

//    入栈
    public void push(int Value){
//        先判断是否满
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        Stack[++top]=Value;
    }

//    出栈
    public int pop(){
//        先判断栈是否为空
        if (isEmpty()){
//            若为空，则抛出异常
            throw new RuntimeException("栈空,无数据");
        }
        return Stack[top--];
    }

//    遍历显示栈
    public void show(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >=0; i--) {
            System.out.println("Stack["+i+"]-------"+Stack[i]);
        }
    }

//    显示栈顶的数据
    public int peek(){
        //        先判断栈是否为空
        if (isEmpty()){
//            若为空，则抛出异常
            throw new RuntimeException("栈空,无数据");
        }
        return Stack[top];
    }
}