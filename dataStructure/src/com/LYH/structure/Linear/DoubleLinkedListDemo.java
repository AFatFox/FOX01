package com.LYH.structure.Linear;

import java.util.Scanner;

public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        NodeDouble node1=new NodeDouble(1,"松江");
        NodeDouble node2=new NodeDouble(3,"吴用");
        NodeDouble node3=new NodeDouble(2,"卢俊义");
        NodeDouble node4=new NodeDouble(3,"吴用");
        NodeDouble node5=new NodeDouble(4,"牛蛙一号");
        NodeDouble node6=new NodeDouble(5,"牛蛙二号");
        NodeDouble node7=new NodeDouble(6,"牛蛙三号");
        NodeDouble node8=new NodeDouble(7,"牛蛙四号");
        NodeDouble node9=new NodeDouble(8,"牛蛙五号");
        NodeDouble node10=new NodeDouble(10,"牛蛙五号");
        NodeDouble node0[]={node1,node2,node3,node4,node5,node10,node6,node7,node8,node9};
        DoubleLinkedList linkedList=new DoubleLinkedList();
        linkedList.add(node0);

        Scanner sc=new Scanner(System.in);
        String n;
        while(true) {
            System.out.print("0,退出\n" + "1,显示所有\n" + "2,删除\n");
            n = sc.next();
            if (n.equals("0")) {
                break;
            }else if(n.equals("1")){
                linkedList.list();
            }else if (n.equals("2")){
                int K=0;
                String K1;
                while (true){
                    System.out.println("请输入K值");
                    K1=sc.next();
//                    为保证程序的健壮性，这里防止用户输入的不是数字，此处做一个整形转换，用try catch包裹一下，转换失败则让用户重新输入
                    try {
                        int k= Integer.parseInt(K1);
                        linkedList.delect(k);
                        break;
                    }catch (Exception e){
                        System.out.println("输入错误");
                        continue;
                    }
                }
            }
        }
        System.out.println("退出成功");
    }
}

//管理链表的类
class DoubleLinkedList{
    private NodeDouble head=new NodeDouble(0,"");  //创建头结点，头结点不存放数据
    private NodeDouble temp=null;  //temp用于保存head

//    插入节点
    public void add(NodeDouble node1[]){  //这里设置的行参是数组是为了方便一次出入多个节点
        NodeDouble node=null;  //先创建一个node类型的变量，用于后面从数组里取数据
        temp = head;  //初始化temp
        boolean flag;  //定义一个标记，在后面用于判断节点编号是否重复
        for (int i = 0; i <node1.length ; i++) {  //这里的for循环不重要，用于遍历得到数组中的数据
            flag=false;   //每次循环进来把flag默认设置为flash
            node=node1[i];      //把数组中的数据放在变量中
            while (temp.next != null) {  //while循环体用于比较节点的编号，根据链表从小到大的顺序来插入数据
//                此处对链表进行遍历下面是依次比较链表中节点编号的大小，当找到的节点编号比传入节点编号大的时候，则跳出循环
                if (node.no < temp.next.no) {
                    break;
                } else if (node.no == temp.next.no) {  //当编号重复，则令flag变为true
                    flag=true;
                    break;
                }
                temp = temp.next;
            }
            if(flag) {  //当flag为true，表示编号重复，则跳过这次的for循环
                System.out.println(node.no+"编号重复，此节点自动忽略");
                continue;
            } else if (temp.next == null) {  //当temp.next==null，则表示遍历到链表尾，直接节点添加到链表尾
//                下面两行用于将节点连接到链表尾
                temp.next = node;
                node.pre = temp;
            } else {
//                节点插入位置不是尾部则需要四次操作
                node.next = temp.next;
                temp.next.pre = node;
                temp.next = node;
                node.pre = temp;
            }
        }
    }

    //    显示链表所有的数据，和单链表一模一样
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

    //    删除。删除操作比单链表节点，不需要刻意的留存待删除节点的上一个节点
    public void delect(int no){
        if (head.next==null){  //正常的先判断链表是否为空
            System.out.println("链表为空");
            return;
        }
//        遍历 查找
        temp=head.next;  //直接初始化temp为链表第一个有效节点
        while (true){   //while循环用于遍历链表查找要删除的节点
            if (temp==null){  //当temp==null，则表示遍历结束了，此时若还未找到，则表示链表中没有对应编号的数据
                System.out.println("未找到");
                return;
            }
            if (temp.no==no){  //找到直接退出循环
                break;
            }
            temp=temp.next;  //更新temp值
        }
//        找到要删除的节点后，使此节点的前一个节点和此节点的后一个节点相连接，那么此节点不被引用，则会被垃圾回收
        temp.pre.next=temp.next;
    }
}

//创建节点类
class NodeDouble{
    public int no;
    public String name;
    public NodeDouble next=null;  //next用于指向节点的下一个节点
    public NodeDouble pre=null;  //pre用于指向节点的上一个节点

    public NodeDouble(int no, String name) {
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