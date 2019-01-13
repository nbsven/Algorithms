package com.abra.algorithms.task_2;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.apache.commons.lang.ArrayUtils;

public class Task2 {

  private static PrintStream output;

  public static void main(String[] args) throws IOException {

    output = new PrintStream(new FileOutputStream("src/main/resources/Task2/output.txt"));

    try (BufferedReader input = new BufferedReader(new FileReader("src/main/resources/Task2/input.txt"))) {
      Integer N = Integer.valueOf(input.readLine());

      int[] array = Arrays.stream(input.readLine().split("\\s"))
          .mapToInt(Integer::valueOf)
          .toArray();

      mergeSort(array, 0, array.length);

      String outputString = Arrays.stream(array)
          .mapToObj(String::valueOf)
          .collect(Collectors.joining(" "));

      output.print(outputString);
    } finally {
      output.close();
    }
  }

  private static void mergeSort(int[] mas, int startInclusive, int endExclusive) {
    int length = endExclusive - startInclusive - 1;

    if (length > 2) {
      mergeSort(mas, startInclusive, startInclusive + length / 2 + 1);
      mergeSort(mas, startInclusive + length / 2 + 1, endExclusive);
    }

    mergeFuntion(mas, startInclusive, startInclusive + length / 2 + 1, startInclusive + length / 2 + 1, endExclusive);

    output.printf("%d %d %d %d\n", startInclusive + 1, endExclusive, mas[startInclusive], mas[endExclusive - 1]);
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
