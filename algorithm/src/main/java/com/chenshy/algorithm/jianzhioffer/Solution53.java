package com.chenshy.algorithm.jianzhioffer;

/**
 * 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 *
 * @author chenshuangyan <chenshuangyan@baijiahulian.com>
 * Created on 2021-07-21
 */
public class Solution53 {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        int left = 0;
        int right = nums.length - 1;


        while (left < right) {
            int min = (left + right) / 2;
            if (nums[min] >= target) {
                right = min;
            } else if (nums[min] < target) {
                left = min + 1;
            }
        }

        while (left < nums.length && nums[left] == target) {
            count++;
            left++;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        System.out.println(new Solution53().search(nums, 8));
    }

}
