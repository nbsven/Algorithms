package com.abra.algorithms.task_2;

import org.apache.commons.lang.ArrayUtils;

public class Task2 {

  public static void main(String[] args) {
    int[] mas = {7, 1, 2, 6, 4, 5, 3, 8};

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

    int[] result = sortArray(left, right);

    copyToArray(mas, result, leftStartIn, rightEndEx);
  }

  private static int[] sortArray(int[] left, int[] right) {
    int leftIndex = 0;
    int rightIndex = 0;
    int resultIndex = 0;
    int allElements = left.length + right.length;
    int[] resultArray = new int[allElements];

    while (resultIndex < allElements) {
      if (leftIndex == left.length) {
        for (; resultIndex < allElements; ) {
          resultArray[resultIndex++] = right[rightIndex++];
        }
        break;
      }

      if (rightIndex == right.length) {
        for (; resultIndex < allElements; ) {
          resultArray[resultIndex++] = left[leftIndex++];
        }
        break;
      }

      if (right[rightIndex] <= left[leftIndex]) {
        resultArray[resultIndex++] = right[rightIndex++];
      } else {
        resultArray[resultIndex++] = left[leftIndex++];
      }
    }

    return resultArray;
  }

  private static void copyToArray(int[] mas, int[] result, int startInclusive, int endExclusive) {
    int k = 0;
    for (int i = startInclusive; i < endExclusive; i++) {
      mas[i] = result[k++];
    }
  }
}
