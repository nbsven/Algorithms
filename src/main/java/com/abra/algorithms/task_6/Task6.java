package com.abra.algorithms.task_6;

import com.abra.algorithms.task_6.b_tree.Btree;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class Task6 {

  public static void main(String[] args) throws Exception {
    try (BufferedReader input = new BufferedReader(new FileReader("src/main/resources/Task6/input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task6/output.txt"))) {
      Integer N = Integer.valueOf(input.readLine());

      Btree tree = new Btree(2);

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
            output.write(tree.getRootElements() + "\n");
            break;
          case "+":
            tree.add(X);
            output.write(tree.getRootElements() + "\n");
            break;
        }
      }
    }
  }

}
