package com.abra.algorithms.task_6.b_tree;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Node {

  private List<Integer> elements = new ArrayList<>();
  private List<Node> children = new ArrayList<>();
}
