package com.LYH.structure.Linear;

public class Calculator {
    public static void main(String[] args) {
        CalculatorDemo calculator=new CalculatorDemo();
        System.out.println(calculator.calculate("1-2*2+3"));
    }
}
class ArrayStack2{
    private int top=-1; //栈顶，初始化为-1
    private int maxSize;  //栈最大容量
    private int[] Stack;  //栈底层用数组实现

    //    构造方法，对栈初始化
    public ArrayStack2(int maxSize) {
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

//    返回栈顶的值，但并不是出栈
    public int peek(){  //这里不用抛出异常，后面用到这个方法是栈里一定有数据的情况下
        return Stack[top];
    }
}
class CalculatorDemo{
    private int priority(int oper){//获得运算符优先级
        if (oper=='/'||oper=='*'){
            return 1;
        }else if (oper=='+'||oper=='-'){
            return 0;
        }else {
            return -1;
        }
    }
    private boolean isOper(int val){
        return val=='*'||val=='/'||val=='-'||val=='+';
    }//判断传入的字符是否是运算符
    private int cal(int num1,int num2,int pre){  //运算
        int res=0;
        switch (pre){
            case '+':
                res = num1+num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
        }
        return res;
    }
    public int calculate(String str){  //接受一个中缀表达式的字符串
        ArrayStack2 numStack=new ArrayStack2(50);
        ArrayStack2 operStack=new ArrayStack2(50);
        int index,num1,num2,oper,res;
        //index作为字符数组索引，num1和num2用来接收从栈中取出的数字，oper用来接收从栈中取出的运算符，res接收num1、num2、oper运算出的结果
        int sum;  //在分析中缀表达式的时候，数字可能是多位数，sum用来整合数字
        char[] chars=str.toCharArray();  //直接通过字符串的toCharArray()方法把字符串转为字符数组

        for (index = 0; index < chars.length; index++) {  //遍历循环此数组
//            当字符为运算符
            if (isOper(chars[index])){
                while (true) {
                    //                先判断符号栈是否为空
                    if (!operStack.isEmpty()) {
                        //                    不为空则先判断当前字符和符号栈顶字符的优先级
                        if (priority(chars[index]) <= priority(operStack.peek())) {
                            //                      若栈顶的级别高或和当前运算符优先级相等，则把栈顶的符号出栈
                            oper = operStack.pop();
                            //                      再从数字栈中出栈两个数字num1，num2
                            num1 = numStack.pop();
                            num2 = numStack.pop();
                            //                        然后将其进行运算
                            res = cal(num1, num2, oper);
                            //                        然后将得到的结果如数字栈
                            numStack.push(res);
                        } else {
                            //                        栈顶级别低，则直接当前符号入栈
                            operStack.push(chars[index]);
                            break;
                        }
                    }else {
                        //                    为空则直接入栈
                        operStack.push(chars[index]);
                        break;
                    }
                }
            }else {
                sum=chars[index]-48;
//                当字符为数字，这里整合多位数
                while (index+1<chars.length&&!isOper(chars[index+1])){
                    sum=sum*10+(chars[index+1]-48);
                    index++;
                }
                numStack.push(sum);
            }
        }
        while (!operStack.isEmpty()){
//            对残留在栈中的数字字符依次运算
            oper=operStack.pop();
            num1=numStack.pop();
            num2=numStack.pop();
            res=cal(num1,num2,oper);
            numStack.push(res);
        }
        return numStack.pop();
    }
}