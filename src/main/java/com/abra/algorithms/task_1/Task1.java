package com.abra.algorithms.task_1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Task1 {

  public static void main(String[] args) throws IOException {
    InputStream resourceAsStream = Task1.class.getResourceAsStream("Task1/task.txt");

    Queue<Integer> queue = new LinkedList<>();

    try (Stream<String> input = Files.lines(Paths.get("src/main/resources/Task1/input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task1/output.txt"))) {

      input.forEach(line -> {
        String[] split = line.split("\\s");

        if ("?".equals(split[0])) {
          int min = queue.stream()
              .mapToInt(Integer::intValue)
              .summaryStatistics().getMin();

          try {
            output.write(min + "\n");
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        } else if ("-".equals(split[0])) {
          queue.poll();
        } else {
          queue.offer(Integer.valueOf(split[1]));
        }
      });
    }
  }
}
