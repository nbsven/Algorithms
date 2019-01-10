package com.abra.algorithms.task_4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RadixSort {


  public static int[] sort(Iterator<String> iterator, int n, int m, int k) {

    List<List<Integer>> buckets = new ArrayList<>();
    for (int i = 0; i < k && i < m; i++) {
      String line = iterator.next();

      String[] symbols = line.split("\\s");
      Map<Integer, String> indexedSymbols = IntStream.range(0, symbols.length)
          .boxed()
          .collect(Collectors.toMap(Function.identity(), index -> symbols[index]));

      if (i == 0) {
        buckets = new ArrayList<>(indexedSymbols.entrySet().stream()
            .sorted(Comparator.comparing(Entry::getValue))
            .collect(Collectors.groupingBy(Entry::getValue, Collectors.mapping(Entry::getKey, Collectors.toList())))
            .values());

        continue;
      }

      buckets = buckets.stream()
          .flatMap(bucket ->
              new ArrayList<>(bucket.stream()
                  .collect(Collectors.toMap(Function.identity(), indexedSymbols::get)).entrySet().stream()
                  .sorted(Comparator.comparing(Entry::getValue))
                  .collect(
                      Collectors.groupingBy(Entry::getValue, Collectors.mapping(Entry::getKey, Collectors.toList())))
                  .values()).stream())
          .collect(Collectors.toList());

    }

    return buckets.stream()
        .flatMap(List::stream)
        .mapToInt(Integer::valueOf)
        .map(i -> i + 1)
        .toArray();
  }
}
