package com.abra.algorithms.task_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.Test;

public class Task4Test {

  @Test
  public void acceptance() throws IOException {
    InputStream inputStream = getClass().getResourceAsStream("/Task4/input.txt");


    try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        BufferedWriter bw = new BufferedWriter(new FileWriter("src/main/resources/Task4/output.txt"))) {

      int[] firstLine = Stream.of(br.readLine().split(" "))
          .mapToInt(Integer::valueOf)
          .toArray();

      int N = firstLine[0];
      int M = firstLine[1];
      int K = firstLine[2];

      Iterator<String> iterator = IteratorOverReader.getIterator(br);

      int[] sort = RadixSort.sort(iterator, N, M, K);

      String outputString = IntStream.of(sort)
          .mapToObj(String::valueOf)
          .collect(Collectors.joining(" "));

      bw.write(outputString);
    }
  }
}