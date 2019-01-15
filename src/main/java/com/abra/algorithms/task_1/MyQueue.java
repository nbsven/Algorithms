package com.abra.algorithms.task_1;

import java.util.Deque;
import java.util.LinkedList;
import javafx.util.Pair;

public class MyQueue {

  Deque<Pair<Integer, Integer>> stack1 = new LinkedList<>();
  Deque<Pair<Integer, Integer>> stack2 = new LinkedList<>();

  public void push(Integer new_element) {
    int minima = stack1.isEmpty() ? new_element : Math.min(new_element, stack1.peek().getValue());

    stack1.push(new Pair<>(new_element, minima));
  }

  public Integer pop() {
    if (stack2.isEmpty()) {
      while (!stack1.isEmpty()) {
        int element = stack1.peek().getKey();
        stack1.pop();

        int minima = stack2.isEmpty() ? element : Math.min(element, stack2.peek().getValue());
        stack2.push(new Pair<>(element, minima));
      }
    }

    return stack2.pop().getKey();
  }

  public Integer getMin() {
    if (stack1.isEmpty() || stack2.isEmpty()) {
      return stack1.isEmpty() ? stack2.peek().getValue() : stack1.peek().getValue();
    } else {
      return Math.min(stack2.peek().getValue(), stack1.peek().getValue());
    }
  }
}
