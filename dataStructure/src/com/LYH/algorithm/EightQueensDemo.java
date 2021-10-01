package com.LYH.algorithm;

public class EightQueensDemo {
    public static void main(String[] args) {
        EightQueens queens=new EightQueens(8); //传入8，我们先求8皇后的问题。传入n便求n皇后
        queens.put(0);  //固定值，传入初始值0，就跟燃油汽车只能添加汽油一样，此处只能传入固定值0才能正常运行
        System.out.println("共有"+queens.getCount()+"摆放方式");
    }

}
class EightQueens{
    private int count=0;
    private int max;  //这里的max代表棋盘规格和几个皇后
    private int[] checkerboard;
    public EightQueens(int max) {
//        求任意数量皇后的问题把传入为对应的值即可
        this.max = max;
//        同时初始化棋盘
        checkerboard=new int[max];
    }


//    用一个一维数组来表示棋盘，索引代表第几行，checkerboard[索引]代表第几列

    private boolean judge(int n){
//        判断第n个皇后和前面的所有皇后是否冲突
        for (int i = 0; i < n; i++) {
//            i和n代表第几行，checkerboard[i]和checkerboard[n]代表第几列
            if (checkerboard[i]==checkerboard[n]||Math.abs(n-i)==Math.abs(checkerboard[n]-checkerboard[i])){
//                checkerboard[i]==checkerboard[n]表示在同一列上。
//                以i所在那一行为底，n所在那一列为高，i、n和高和底的交点为定点作三角形，
//                则Math.abs(n-i)表示三角形的高长，Math.abs(checkerboard[n]-checkerboard[i])表示三角形的底长
//                若Math.abs(n-i)==Math.abs(checkerboard[n]-checkerboard[i]则表示这是一个等腰直角三角形,即i和n两点在同一条45度斜线上
                return false;
            }
        }

//        不用判断是否在同一行，后面程序设计上会自动避免这个问题
        return true;
    }


    public int getCount() {
        return count;
    }
    //    整体思路：
//    用一个一位数组来代表棋盘，一维数组的索引表示第几行，和该放第几个皇后，他的每个索引对应的值表示在当前行上的第几列
//    然后准备开始放置，在每次放置前，先判断是否已经摆放了8个皇后
//    开始放置，这里用暴力放置法，每行的每个格子都试一遍，尝试试出所有的放置方法
//    通过for循环和递归和递归的回溯来实现
//    for循环用来表示某一行的所有格子的放置，递归表示进入下一行的放置，回溯表示回到上一行的放置
//    首先把第0个皇后(我们从0开始)放在第0行第0列上，然后进行下一行的放置，找到合适位置，则继续进行其他行的放置尝试，若某一行找不到合适的位置，则进行回溯，回溯到上一行从新找其他的合适位置
//    若都能找到，即找打了一个正确的放置，则开始进行回溯。每次回溯到第0行，则表示第0行的此次for循环，即第0个皇后在此处的放置(每次for循环都是在此行的一处放置)，其他皇后已经找到了所有可能放置方法
//    然后进入下一个位置的放置，即for循环

    //回溯原理：
//    假设最后一行找到了一个正确的摆放位置，但是此时for循环未停止，则会继续for循环，继续在最后一行向后寻找正确的摆放位置，若最后一行找到头了，即for循环结束了
//    则回溯到上一行，上一行则再重新向后找一个正确的摆放位置，
//    若找到了，则再递归到最后一行，此时上一行是一个新的正确摆放位置，再这样的条件下，在最后一行再从第0个格子重新再寻找正确摆放位置，再把最后一行遍历一遍
//    若未找到，则返回上上行，再在上上寻找一个新的正确的摆放位置，再重复后面行的重新寻找正确摆放位置的操作，一直套娃，一直回溯递归
    public void put(int n){
//        每次递归或回溯，判断是否8个皇后全部放好
        if (n==max){
            count++;
            show();
//            如果8个已经放好，则跳出这次递归，进行回溯，进行上一次递归未运行完的for循环
            //这里，每次到这里其实第n个皇后还未摆放，因为后面每次传入的参数是n+1，这里只是判断，还并未真正摆放
            // 因为n是从0开始的，所以到这里也是已经摆放了8个
            return;
        }
        for (int i = 0; i < max; i++) {//每次递归都会有这一个的8次的for循环，来尝试在此行的每一个格子上放置皇后
//            这里有8此循环，下面放皇后，放在第i个格子上。正好8此循环，就等于把皇后在同一行上的所有格子都试一遍
//            每次n递增，正好n可同时表示该放第几个皇后，和放在哪一行
            checkerboard[n]=i;

//            每次放好后，进行判断，是否与其他的皇后冲突
            if(judge(n)){  //若不冲突，则进入下一次递归，即进入下一行的放置尝试
                put(n+1);
            }else {
//                若冲突，则跳过此次for循环，进入下次循环，即把该皇后在此行上向后移一位(即i+1，i表示列，n表示行)
//                若这一行都试完了(即for循环结束了)，都不行，则此次的递归就会自动结束了(循环运行完了，代码运行完了)，
//                然后回溯到上一次的递归(就等于回到了上一行)，跳过此行中皇后放置正确的那一次for循环，继续for循环，寻找下一个在这一行可以正确防止皇后的位置
//                若找到了，则又进入递归，再重复上面的过程。若一直没找到，则再次回溯的更上面的行。
                continue;
            }
        }
    }

    private void show(){
//        打印棋盘，此处即一维数组
        System.out.print("一种摆放方式是：");
        for (int i = 0; i < checkerboard.length; i++) {
            System.out.print(checkerboard[i]+" ");
        }
        System.out.println();
    }
}


