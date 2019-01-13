package com.abra.algorithms.task_8;

import com.abra.algorithms.task_7.MySet;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Task8 {

  public static void main(String[] args) throws IOException {
    try (BufferedReader input = new BufferedReader(new FileReader("src/main/resources/Task7/input.txt"));
        BufferedWriter output = new BufferedWriter(new FileWriter("src/main/resources/Task7/output.txt"))) {
      Integer N = Integer.valueOf(input.readLine());


//      for (int i = 0; i < N; i++) {
//        String line = input.readLine();
//
//        String[] split = line.split("\\s");
//        int X = Integer.parseInt(split[1]);
//
//        switch (split[0]) {
//          case "?": {
//            output.write(set.contains(X) + "\n");
//            break;
//          }
//          case "-":
//            set.remove(X);
//            break;
//          case "+":
//            set.add(X);
//            break;
//        }
//      }
    }
  }

}
