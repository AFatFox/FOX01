package com.LYH.structure.Linear;

import java.util.Scanner;
import java.util.Stack;

public class SingleLinkedListDemo {
    public static void main(String[] args)   {
        Node node1=new Node(1,"松江");
        Node node2=new Node(3,"吴用");
        Node node3=new Node(2,"卢俊义");
        Node node4=new Node(3,"吴用");
        Node node5=new Node(4,"牛蛙一号");
        Node node6=new Node(5,"牛蛙二号");
        Node node7=new Node(6,"牛蛙三号");
        Node node8=new Node(7,"牛蛙四号");
        Node node9=new Node(8,"牛蛙五号");
        Node node10=new Node(10,"牛蛙五号");
        Node node[]={node6,node7,node8,node9};
        Node node0[]={node1,node2,node3,node4,node5,node10};
        SingleLinkedList linkedList=new SingleLinkedList();
        SingleLinkedList linkedList1=new SingleLinkedList();
        linkedList.add(node);
        linkedList1.add(node0);
        Scanner sc=new Scanner(System.in);
        String n="1";
        while(true){
            System.out.print("0,退出\n"+"1,修改\n"+"2,显示所有\n"+"3,删除\n"+"4,得到倒数第K个节点\n"+"5,链表反转\n"+"6,链表反向打印\n"+"7,两链表合并\n");
            n = sc.next();
            if(n.equals("0")){
                break;
            }else if (n.equals("1")){
                linkedList.update(new Node(6,"大头"));
                linkedList.list();
            }else if(n.equals("2")){
                linkedList.list();
            }else if(n.equals("3")){
                linkedList.delect(3);
                linkedList.list();
            }else if(n.equals("4")){
                int K=0;
                String K1=null;
                while (true){
                    System.out.println("请输入K值");
                    K1=sc.next();
                    try {
                        int k= Integer.parseInt(K1);
                        break;
                    }catch (Exception e){
                        System.out.println("输入错误");
                        continue;
                    }
                }

                try {
                    Node node000=linkedList.getLastNode(K);
                    System.out.println(node000);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
            }else if(n.equals("5")){
                linkedList.reversal();
                linkedList.list();
            }else if(n.equals("6")){
                linkedList.reversalShow();
            }else if(n.equals("7")){
                linkedList.merge(linkedList1);
                linkedList.list();
            }else {
                System.out.println("输入错误");
            }
        }
        System.out.println("退出成功");
    }
}

//管理链表的类
class SingleLinkedList{
//    创建头结点，头结点仅作为一个标记，不存放数据
    private Node head=new Node(0,"");
    private Node temp;    //防止head头结点发生变动，此处创建一个节点用来代替头结点
//    增加节点的方法
    public void add(Node[] nodeArray){
        temp=head;

//        将遍历链表的操作和插入节点的操作分开

//        设置一个标记，用于判断编号是否重复
        boolean flag;
//        遍历，按照编号从小到大，找到新增节点应该插入的位置
        for (int i = 0; i < nodeArray.length; i++) {
//            设置初始值为flase，当编号重复，则设置flag变为true
            flag = false;
            while (true) {
                if (temp.next == null) {
                    break;
                }
                if (temp.next.no > nodeArray[i].no) {
                    break;
                }
                if (temp.next.no == nodeArray[i].no) {
                    flag = true;
                    break;
                }
                temp = temp.next;
            }
//            插入操作
            if (flag) {
                System.out.println(nodeArray[i].no + "编号重复");
                continue;
            } else{
                    nodeArray[i].next=temp.next;
                    temp.next=nodeArray[i];
            }
        }
    }

//    删除
    public void delect(int no){
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
//        遍历 查找
        temp=head;
        while (true){
            if (temp.next==null){
                System.out.println("未找到");
                return;
            }
            //    删除需要用temp.next去查找，不能用temp。
            //    如果用temp当找到要被删除的数据，则五法找到其前一个节点，然后就没法删除
            if (temp.next.no==no){
                break;
            }
            temp=temp.next;
        }
//        找到要删除的节点后，使此节点的前一个节点和此节点的后一个节点相连接，那么此节点不被引用，则会被垃圾回收
        temp.next=temp.next.next;
    }

//    显示链表所有的数据
    public void list(){
        if (head.next==null){
            System.out.println("链表空");
        }else {
            temp=head.next;   //头结点无数据，所以直接让temp指向第二个节点
            while (temp!=null){
                System.out.println(temp);
                temp=temp.next;
            }
        }

    }

//    修改节点
    public void update(Node node){
//        首先判断链表是否为空
        if (head.next==null){
            System.out.println("链表为空");
            return;
        }
        temp=head.next;

//        这里遍历查找对应编号的节点，遍历至使temp指向要修改的节点
        while (true){
            if (temp==null){
//                遍历到链表尾那就说明未找到，直接return
                System.out.println("未找到");
                return;
            }
            if (temp.no==node.no){
                break;
            }
            temp=temp.next;
        }

//        找到后对链表数据进行修改
        temp.name=node.name;
    }

//    得到有效节点个数
    public int getLong(){
        temp=head;
        int n=0;
        while(temp.next!=null){
            temp=temp.next;
            n++;
        }
        return n;
    }

//    得到倒数第K个节点
    public Node getLastNode(int n){
        int Long=getLong();  //得到有效节点数
        if (Long==0){
            throw new RuntimeException("链表为空");
        }
        temp=head.next;
        for (int i = 0; i < Long-n; i++) {  //遍历
            temp=temp.next;
        }
        return temp;
    }

//    链表反转
    public void reversal(){
        if (head.next==null||head.next.next==null){  //当链表只有1个节点或没有则无需反转，然后退出
            System.out.println("数据不足，无需反转");
            return;
        }
        Node head1=new Node(0,"");    //创建一个新链表，此处是先创建一个头
        temp=head.next;                      //初始化temp，temp是head的代执行人
        Node nextNode =null;  //当把temp从原链表删去增加到新链表中的时候，我们就找不到原链表的剩下节点了，则需要一个节点来记录temp后面的节点
        while (temp!=null) {           //temp不为空，则表示原链表可能还有节点
            nextNode = temp.next;         //第一步，记录下temp后面的节点
            temp.next=head1.next;    //第二步，让temp的next域指向新链表的第一个有效节点
            head1.next=temp;        //第三步，新链表头的next域指向新加入的节点，二三步进行头插法
            temp=nextNode;             //第四步，更新temp，使用nextNode这个节点
        }
        head.next=head1.next;     //把原节点头指向新节点的第一个有效节点
    }

//    链表反向打印
    public void reversalShow(){
        if (head.next==null){
            System.out.println("链表空");
            return;
        }
        temp=head.next;
        Stack<Node> stack=new Stack<Node>();  //创建栈
        while (temp!=null){                  //取出节点，放在栈中
            stack.push(temp);
            temp=temp.next;
        }
        while (stack.size()>0){            //遍历栈，打印
            System.out.println(stack.pop());
        }
    }

//    得到头结点
    public Node getHead(){
        return head;
    }
//    把另一个链表并入此链表
    public void merge(SingleLinkedList linkedList1){
        int n=linkedList1.getLong();   //得到其中一个链表的有效节点个数，用于创建数组
        temp=linkedList1.getHead().next;   //得到其中一个链表的第一个有效节点
        if (n==0){
            System.out.println("第二联表为空");
            return;
        }
        Node node[]=new Node[n];     //创建数组
        for (int i = 0; i < n; i++) {    //遍历其中一个链表，并向数组增加数据
            node[i]=temp;
            temp=temp.next;
        }
        add(node);                 //插入
    }
}

//创建节点类，此处的属性可都设置为公共属性，因为这个类只是我们内部使用，不会暴露出去
class Node{
    public int no;
    public String name;
    public Node next=null;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "node{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}