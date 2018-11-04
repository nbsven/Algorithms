package com.abra.algorithms.task_7;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import org.junit.Assert;
import org.junit.Test;

public class MySetTest {

  @Test
  public void setTest() {
    MySet mySet = new MySet();
    Set<Integer> set = new HashSet<>(1_000_000);

    ThreadLocalRandom.current().ints(1_000_000, 0, 1_000_000_000)
        .forEachOrdered(i -> {
          mySet.add(i);
          set.add(i);
        });

    long count = set.stream()
        .filter(mySet::contains)
        .count();

    Assert.assertEquals(count, set.size());
  }
}