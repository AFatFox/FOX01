package com.LYH.structure.Nonlinearity;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class SparseArray {
    @Test
    public void test01() {
        ReductionArray(getSparse(getChessArray()));
    }
    public int[][] getChessArray(){
//        创建一个11*11的棋盘(二维数组)
        int chessArray[][]=new int[11][11];
//        为数组赋值，0代表为空，1代表黑子，2代表白子，int型的数组中0是默认的值
        chessArray[1][2]=1;
        chessArray[2][3]=2;
        for(int[] ints : chessArray){
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        return chessArray;
    }
    public int[][] getSparse(int[][] chessArray){
//        将数组转换为稀疏数组
//        首先得到有几个有效数值，即不为0的
        List<Integer> rows=new LinkedList();  //存储有效数据所在列
        List<Integer> cols=new LinkedList();  //存储有效数据所在行
        List<Integer> c=new LinkedList();  //存储有效数据的值
        int count=0;  //存储有效数据的个数
        for (int i=0;i<chessArray.length;i++) {
            for (int j=0;j<chessArray[i].length;j++) {
                if (chessArray[i][j]>0){
                    rows.add(count,i);
                    cols.add(count,j);
                    c.add(count,chessArray[i][j]);
                    System.out.println(rows.get(count)+"----"+cols.get(count)+"----"+c.get(count));
                    count++;
                }
            }
        }
//        创建稀疏数组
        int[][] SparseeArray=new int[count+1][3];
//        存储信息
        SparseeArray[0][0]=chessArray.length;
        SparseeArray[0][1]=chessArray[0].length;
//        count值为2即总共有两个关键值
        SparseeArray[0][2]=count;
        for(int i=1;i<count+1;i++){
            SparseeArray[i][0]=rows.get(i-1);
            SparseeArray[i][1]=cols.get(i-1);
            SparseeArray[i][2]=c.get(i-1);

        }
        for(int[] ints : SparseeArray){
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        return SparseeArray;
    }
//    还原数组
    public int[][] ReductionArray(int [][] SparseArray){
//        根据稀疏数组中存储的行列值创建一个数组
        int[][] IntArray=new int[SparseArray[0][0]][SparseArray[0][1]];

//        遍历数组，向数组中存放关键的值
        for (int i = 0; i <SparseArray[0][2] ; i++) {
            IntArray[SparseArray[i+1][0]][SparseArray[i+1][1]]=SparseArray[i+1][2];
        }
//        打印
        for(int[] ints : IntArray){
            for (int anInt : ints) {
                System.out.print(anInt+"\t");
            }
            System.out.println();
        }
        return IntArray;
    }
}
