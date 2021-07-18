package com.chenshy.algorithm;

/**
 * 在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 * [
 * [1,2,8,9],
 * [2,4,9,12],
 * [4,7,10,13],
 * [6,8,11,15]
 * ]
 * 给定 target = 7，返回 true。
 * <p>
 * 给定 target = 3，返回 false。
 *
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-16
 */
public class FindArray {

    public boolean Find(int target, int[][] array) {

        if (array == null || array.length == 0) {
            return false;
        }
        int row = 0;
        int col = array[0].length - 1;
        while (row < array.length && col >= 0) {
            if (array[row][col] == target) {
                return true;
            }
            if (array[row][col] > target) {
                col--;
                continue;
            }
            if (array[row][col] < target) {
                row++;
                continue;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] array = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int target = 7;

        int[][] array2 = {{1, 2, 8, 9}, {2, 4, 9, 12}, {4, 7, 10, 13}, {6, 8, 11, 15}};
        int target2 = 5;
        FindArray solution = new FindArray();
        System.out.println(solution.Find(target, array));
        System.out.println(solution.Find(target2, array2));

    }
}
