package com.abra.algorithms.task_6.b_tree;

import java.util.LinkedList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node {

  private List<Integer> elements = new LinkedList<>();
  private List<Node> children = new LinkedList<>();
}
