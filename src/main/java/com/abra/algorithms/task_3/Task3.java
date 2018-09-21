package com.abra.algorithms.task_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;

public class Task3 {

  public static void main(String[] args) throws IOException {

    try (BufferedReader input = new BufferedReader(new FileReader("src/main/resources/Task3/input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task3/output.txt"))) {
      int N = Integer.valueOf(input.readLine());

      IntStream.range(1, N + 1)
          .map(i -> ~i)
          .sorted()
          .map(i -> ~i)
          .forEachOrdered(i -> {
            try {
              output.write(i + "\n");
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
    }
  }
}
