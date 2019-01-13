package com.abra.algorithms.task_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Iterator;

public class IteratorOverReader {

  public static Iterator<String> getIterator(BufferedReader br) {
    return new Iterator<String>() {
      @Override
      public boolean hasNext() {
        try {
          return br.ready();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public String next() {
        try {
          return br.readLine();
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
    };
  }
}
