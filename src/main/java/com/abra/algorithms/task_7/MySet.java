package com.abra.algorithms.task_7;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class MySet {

  private static final int BUCKETS_SIZE = 1_000;
  private List<List<Integer>> buckets = new ArrayList<>(BUCKETS_SIZE);

  public MySet() {
    for (int i = 0; i < BUCKETS_SIZE; i++) {
      buckets.add(i, new LinkedList<>());
    }
  }

  private int calculateHash(Integer i) {
    return i % BUCKETS_SIZE;
  }

  public void add(Integer i) {
    List<Integer> bucket = buckets.get(calculateHash(i));

    if (bucket.contains(i)) {
      return;
    }

    bucket.add(i);
  }

  public void remove(Integer i){
    List<Integer> bucket = buckets.get(calculateHash(i));

    bucket.remove(i);
  }

  public boolean contains(Integer i){
    return buckets.get(calculateHash(i)).contains(i);
  }

}
