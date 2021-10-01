package com.LYH.structure.Linear;


public class Josephu {
    public static void main(String[] args) {
        CircularLink Link=new CircularLink();
        Link.add(100);
        //    n:共有多少学生 m:从第几个学生开始报数 k:每一次需要报几下
        Link.JosephuDemo(3,5);
    }
}
//初始化
class CircularLink{
    Node3 first;   //类似于单链表中的head指向环形链表的"第一个"节点
    Node3 temp;   //辅助，用于完成遍历，增添，解决约瑟夫问题
    public void add(int k){  //初始化环形链表
        temp=null;  //初始化temp
        for (int i = 1; i <= k; i++) {  //根据参数来用for循环构建环形链表
            Node3 node=new Node3(i,"牛蛙");   //创建节点
            if(first==null){   //链表中无数据的时候，将此次循环创建的节点作为first
//                然后单个节点也要构成环，即，使自身指向自身
                first=node;
                node.next=first;
                temp=first;   //并使temp指向first
            }else {  //当链表中有数据
//                向环形链表的"尾"添加数据
                temp.next=node;
                node.next=first;
//                添加完成后，把temp向后移，使其指向环链表"尾"
                temp=node;
            }
        }
    }
//    遍历
    public void list(){
        if (first==null){
            System.out.println("无数据");
            return;
        }
//        初始化temp
        temp=first;
        do {
            System.out.println(temp);
            temp=temp.next;
//            遍历完成的标志是temp==first
        }while(temp!=first);
    }

//    约瑟夫问题的解决
//    m:从第几个学生开始报数 k:每一次需要报几下
    public void JosephuDemo(int m,int k){
        if (first==null){
            System.out.println("无数据");
            return;
        }
//        初始化temp
        temp=first;
        int n=1;  //n表示共有多少学生
        while (temp.next!=first){  //首先通过遍历使temp指向环链表"尾"，即first的上一个节点。此举是为了方便移除学生
//            在遍历的时候顺便用n统计一下共有多少学生
//            注意n的初始值需要是1，因为这里while循环的次数仅仅是一个移动的次数，并不是节点的个数
//            就好比是线段，有头和尾两个点，但是从头到尾只需要移动一次
            n++;
            temp=temp.next;
        }
//        得到n后，判断数据输入是否正确
        if (m>n||m<1||k<1){
            System.out.println("数据出错");
            return;
        }
//        m表示从第几个学生开始数，所以在开始前，需要先将temp和first移动几次进行初始化
//        为什么是m-1？和前面n那里一样，此处m表示的是"个数"，是从第几个学生开始数，这里for循环的次数仅仅是一个移动的次数
        for (int i = 0; i < m-1; i++) {
//            移动
            temp=temp.next;
            first=first.next;
        }
        int p=1;  //用于记录出列的学生是第几次出列
        while (temp!=first){   //环链表中只剩一个学生的判别条件是temp==first，因为当链中数据大于1个时，temp和first一定是相邻的，并不是相同的
            for (int i = 0; i < k-1; i++) {
//          为什么是k-1？和前面m同理，k表示的是"个数"，这里for循环需要表示的是一个移动的次数
                temp=temp.next;
                first=first.next;
            }
            System.out.println("第"+p+"个出列的是"+first);
            first=first.next;  //找到后，将first节点跳到要出列节点的下一个节点，方便后续的遍历出列和判断
            temp.next=first;   //此处执行前，temp和first中间相隔一个节点，，此节点就是出列的节点，删除此节点，
            p++;  //更新p值
        }
        System.out.println("幸存者是"+first);
    }
}
class Node3{
    public int no;
    public String name;
    public Node3 next=null;

    public Node3(int no, String name) {
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