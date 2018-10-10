package com.abra.algorithms.task_5;

import com.abra.algorithms.task_5.tree.Tree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task5 {

  public static void main(String[] args) throws IOException {
    try (BufferedReader input = new BufferedReader(new FileReader("src/main/resources/Task5/input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task5/output.txt"))) {
      Integer N = Integer.valueOf(input.readLine());

      Tree tree = new Tree();

      for (int i = 0; i < N; i++) {
        String line = input.readLine();

        String[] split = line.split("\\s");
        int X = Integer.parseInt(split[1]);

        switch (split[0]) {
          case "?": {
            output.write(tree.contains(X) + "\n");
            break;
          }
          case "-":
            tree.remove(X);
            output.write(tree.getRootBalance() + "\n");
            break;
          case "+":
            tree.insert(X);
            output.write(tree.getRootBalance() + "\n");
            break;
        }
      }
    }
  }
}
