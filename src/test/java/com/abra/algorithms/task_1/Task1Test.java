package com.abra.algorithms.task_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.stream.IntStream;
import org.junit.Test;


public class Task1Test {

  @Test
  public void test() throws IOException {
    try (BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task1/input.txt"))) {
      output.write("1000000\n");

      IntStream.range(1, 1_000_000)
          .map(i -> ~i)
          .sorted()
          .map(i -> ~i)
          .forEachOrdered(i -> {
            try {
              output.write("+ "+ i + "\n?\n");
            } catch (IOException e) {
              e.printStackTrace();
            }
          });
    }
  }
}
