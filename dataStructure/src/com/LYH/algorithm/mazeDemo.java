package com.LYH.algorithm;

import java.util.Map;

public class mazeDemo {
    public static void main(String[] args) {
//        创建二维数组
        int[][] map=new int[8][7];
//        初始化
        for (int i = 0; i < 8; i++) {
            map[i][0]=1;
            map[i][6]=1;
            if (i<7){
                map[0][i]=1;
                map[7][i]=1;
            }
        }
        map[5][5]=1;
        map[5][1]=1;


        Maze maze=new Maze();
//        展示初始地图
        maze.showArray(map);
//        设置起点
        maze.setWay(map,1,1);
//        展示找到结果后的地图
        maze.showArray(map);
    }
}
class Maze{


//    整体思路：设置起始点，在起始点的基础上进行加减，以此来当做点的前后左右移动
//    走过的点用2标记，未走过的用0标记，死点(无法向0点移动的点)用3标记
//    其实整体来看，递归也是一种循环，一直循环同一个方法体
//    这里一直递归，每次移动都调用一下方法体本身，即递归一次
//    假设一个方向撞墙，则在此点的基础上进行其他方向的移动
//    若此点无法移动，为死点，则返回上一个点，在上一个点的基础上再进行移动
    public boolean setWay(int[][] map,int i,int j){
        if (map[4][5]==2){
//            每次递归，则判断是否到终点，终点为map[6][1]，若到终点，则返回true，若不为终点，则进入下面的else中，进行移动寻找终点
            System.out.println("已找到出口");
            return true;
        }else {
            if (map[i][j]==0){ //判断当前点能否移动
                map[i][j]=2;  //当前点可以移动则向当前点移动，并标记此点为2
                if (setWay(map,i+1,j)){
                    //向下移动，并进入下一次递归，若下一次依旧可以向下移动，则再进入下一次递归，若不能，则返回flase，即结束下一次的递归，并返回此次递归，并结束此次的if，进入下一个if
//                    若终点就在下面，则一直向下移动，到达终点的那次递归，进行上面的map[][]==2的if判断结果为true，则此处进入if结构体，则引链式反应，一直返回true，直到方法结束
                    return true;
                }else if (setWay(map,i,j+1)){
//                    进入此次if，即向右移动依然和上面的一样的流程
                    return true;
                }else if (setWay(map,i-1,j)){
//                    向上移动
                    return true;
                }else if (setWay(map,i,j-1)){
//                    向左移动
                    return true;
                }else {
//                    若此点无法向标记为0的点移动，则标记此点为3
                    map[i][j]=3;
                    return false;
                }
            }else { //不能则直接返回flase，返回上一个点
                return false;
            }
        }
    }
    public void showArray(int [][] map){
//        遍历二维数组
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
    }
}
