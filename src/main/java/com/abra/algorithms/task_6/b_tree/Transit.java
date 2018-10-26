package com.abra.algorithms.task_6.b_tree;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transit {

  private boolean isSplited;
  private int splitter;
  private Node left;
  private Node right;

  private Transit(boolean isSplited) {
    this.isSplited = isSplited;
  }

  private Transit(int splitter, Node left, Node right) {
    this.isSplited = true;
    this.splitter = splitter;
    this.left = left;
    this.right = right;
  }

  public Transit split(Node node, int factor) {
    List<Integer> elements = node.getElements();
    List<Node> nodes = node.getChildren();

    if (elements.size() < 2 * factor - 1) {
      return new Transit(false);
    }

    List<Integer> leftElements = elements.subList(0, factor - 1);
    List<Node> leftNodes = nodes.subList(0, factor);

    List<Integer> rightElements = elements.subList(factor, 2 * factor - 1);
    List<Node> rightNodes = nodes.subList(factor, 2 * factor);

    int middle = elements.get(factor - 1);

    Node left = new Node(leftElements, leftNodes);
    Node right = new Node(rightElements, rightNodes);

    return new Transit(middle, left, right);
  }
}
