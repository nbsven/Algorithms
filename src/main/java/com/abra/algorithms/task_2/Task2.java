package com.abra.algorithms.task_2;

import java.util.Arrays;
import org.apache.commons.lang.ArrayUtils;

public class Task2 {

  public static void main(String[] args) {
    int[] mas = {7, 1, 2, 6, 4, 5, 3, 8};

//    mergeFuntion(mas, 0, 4, 4, 8);
    mergeSort(mas, 0, 8);

    for (int i : mas) {
      System.out.println(i);
    }
  }

  private static void mergeSort(int[] mas, int startInclusive, int endExclusive) {
    int length = endExclusive - startInclusive - 1;

    if (length > 2) {
      mergeSort(mas, startInclusive, startInclusive + length / 2 + 1);
      mergeSort(mas, startInclusive + length / 2 + 1, endExclusive);
    }
    
    mergeFuntion(mas, startInclusive, startInclusive + length / 2 + 1, startInclusive + length / 2 + 1, endExclusive);
  }

  private static void mergeFuntion(int[] mas, int leftStartIn, int leftEndEx, int rightStartIn, int rightEndEx) {
    int[] left = ArrayUtils.subarray(mas, leftStartIn, leftEndEx);
    int[] right = ArrayUtils.subarray(mas, rightStartIn, rightEndEx);
    int[] result = ArrayUtils.addAll(left, right);

    Arrays.sort(result);

    int[] leftOutside = ArrayUtils.subarray(mas, 0, leftStartIn);
    int[] rightOutside = ArrayUtils.subarray(mas, rightEndEx, mas.length);

    result = ArrayUtils.addAll(ArrayUtils.addAll(leftOutside, result), rightOutside);
    copyToArray(mas, result);
  }

  private static void copyToArray(int[] mas, int[] result) {
    for (int i = 0; i < mas.length; i++) {
      mas[i] = result[i];
    }
  }
}
