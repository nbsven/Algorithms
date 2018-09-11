package com.abra.algorithms.task_2;

import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

public class Task2 {

  public static void main(String[] args) {
    int[] mas = {1, 3, 5, 6, 2, 4, 7, 8};

    int[] mergeFuntion = mergeFuntion(mas, 0,4,4,8);

    for (int i : mergeFuntion) {
      System.out.println(i);
    }
  }

  private static int[] mergeFuntion(int[] mas, int leftStart, int leftEnd, int rightStart, int rightEnd) {
    int[] left = ArrayUtils.subarray(mas, leftStart, leftEnd);
    int[] right = ArrayUtils.subarray(mas, rightStart, rightEnd);
    int[] result = ArrayUtils.addAll(left,right);

    Arrays.sort(result);

    return result;
  }
}
