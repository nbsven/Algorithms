package com.abra.algorithms.task_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Task1 {

  public static void main(String[] args) throws IOException {
    InputStream resourceAsStream = Task1.class.getResourceAsStream("Task1/task.txt");

    MyQueue queue = new MyQueue();

    try (BufferedReader input = new BufferedReader(new FileReader("src/main/resources/Task1/input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task1/output.txt"))) {
      Integer N = Integer.valueOf(input.readLine());

      for (int i = 0; i < N; i++) {
        String line = input.readLine();

        String[] split = line.split("\\s");

        switch (split[0]) {
          case "?": {
            int min = queue.getMin();

            output.write(min + "\n");
            break;
          }
          case "-":
            queue.pop();
            break;
          case "+":
            queue.push(Integer.valueOf(split[1]));
            break;
        }
      }
    }
  }
}
