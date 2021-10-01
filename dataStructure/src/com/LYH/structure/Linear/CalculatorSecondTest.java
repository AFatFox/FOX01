package com.LYH.structure.Linear;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CalculatorSecondTest {
    public static void main(String[] args) {
        String str="1*(2+3)-6-(6/(1+2))";  //定义一个中缀表达式
        CalculatorSecond Calculator=new CalculatorSecond();
        try {
            System.out.println(str+"="+Calculator.GetResult(str));  //调用方法
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
class CalculatorSecond{
//    第一步，中缀表达式字符串转集合
    public List<String> ReturnList(String str){
//        创建集合，用来存储中缀表达式中的元素
        List<String> StrList=new ArrayList<String>();
        int i=0; //索引，用来遍历中缀字符串

        char c;//c用来暂存字符串中的单个字符
        String string; //string用来暂存中缀表达式中的元素

        do {
//            先判断单个字符是否是运算符，是的话直接存入集合中
            if ((c=str.charAt(i))<48||(c=str.charAt(i))>57){
                StrList.add(c+"");
                i++;
            }else {
//                若不是，则说明是数字，下面判断数字后面是否紧跟有数字，若有，则说明是多位数
                string="";  //初始化string
                while (i<str.length()&&((c=str.charAt(i))>=48&&(c=str.charAt(i))<=57)){
                    string=string+str.charAt(i);  //若为多位数，则进行字符串拼接，将多个数字拼在一起成为多位数，并用string接收
                    i++;
                }
                StrList.add(string); //将string存入集合
            }
        }while (i<str.length());  //循环结束的条件是索引小于字符串的长
        return StrList;  //返回得到的集合
    }
//    中缀转后缀
    public List<String> ReturnRPN(List<String> Str){  //这里接收一个由中缀表达式中的元素构成的集合
        Stack<String> S1=new Stack<String>();       //创建第一个栈S1
        List<String> S2=new ArrayList<String>();   //因为最后得到的栈需要进行逆序，这里为了方便，直接用集合代替S2
        for (String str: Str){
            if (str.matches("\\d+")){  //1. 用正则来判断是否是数字，若元素是数字，则直接入S2。
                S2.add(str);
            }else if (str.equals("(")){    //   2. 若为左括号"("，则直接入S1栈。
                S1.push("(");
            }else if (str.equals(")")){  //     3. 若为右括号，则持续将S1栈顶元素作出栈，并，入S2，直到遇到右括号")"停止
                while (!S1.peek().equals("(")){
                    S2.add(S1.pop());
                }
                S1.pop();//此时再对S1栈作一次出栈操作，是为了将其中的左括号"("删去，此时，右括号也不需要，直接忽略即可
            }else{
//    4. 若为运算符，则判断S1是否为空，若为空则直接如S1，若不为空，则将此元素与S1栈顶的运算符作比较，若此运算符级别高，则直接如S1，若此元素级别低，则将S1栈顶运算符出栈，并，入S2
//        然后再将此元素与栈顶运算符作比较，一直循环，直到S1变为空，或栈顶优先级低于此元素，或遇到左括号"("停止，停止后将此元素入栈
                while (S1.size()!=0&&!S1.peek().equals("(")&&compare(S1.peek())>=compare(str)){
//                为防止出现异常，先判断S1,是否为空，再判断S1栈顶是否是左括号，再判断优先级     compare是自己编写的一个根据运算符返回对应级别的方法
                    S2.add(S1.pop());
                }
                S1.push(str);  //循环结束后，将当前运算符入栈
            }
        }
        while (S1.size()!=0){
//        遍历结束后，将S1栈边出栈边入S2，最后得到的S2，按照从栈底向栈顶顺序来，此即为后缀表达式的栈。所以此处我用集合替代S2，到最后，集合S2从索引0到尾部即为一个后缀表达式集合
//            最后循环遍历S1，将S1中元素入S2
            S2.add(S1.pop());
        }
        System.out.println("S2----"+S2);
        return S2;
    }
//    判断运算符优先级
    public int compare(String string){
        if (string.equals("+")||string.equals("-")){
            return 0;
        }else if (string.equals("*")||string.equals("/")){
            return 1;
        }else {
            throw new RuntimeException("符号错误");
        }
    }
    //    计算后缀表达式
    public int GetResult(String str){
//        计算后缀表达式非常简单，先定义栈，然后对后缀表达式的集合进行遍历。若为数字则直接如栈，若为运算符，则从占中出栈两个元素根据此运算符进行运算，将运算结果再入栈。
//        循环此操作，最后留在栈中的那个元素则为原中缀表达式的结果
        List<String> list=ReturnRPN(ReturnList(str));
        Stack<String> stack=new Stack<String>();
        for (String string:list){
            if (string.matches("\\d+")){
                stack.push(string);
            }else {
                int num1=Integer.parseInt(stack.pop());
                int num2=Integer.parseInt(stack.pop());
                int num=operation(num1,num2,string);
                stack.push(num+"");
            }
        }
        return Integer.parseInt(stack.pop());  //转为整形
    }
    //    运算
    public int operation(int num1,int num2,String str){
        if (str.equals("+")){
            return num1+num2;
        }else if (str.equals("-")){
            return num2-num1;
        }else if (str.equals("*")){
            return num1*num2;
        }else if(str.equals("/")){
            return num2/num1;
        }else {
            throw new RuntimeException("运算出错");
        }
    }
}