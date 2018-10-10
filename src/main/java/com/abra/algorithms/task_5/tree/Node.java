package com.abra.algorithms.task_5.tree;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node {

  private int key;
  private int height;
  private Node left;
  private Node right;

  public Node(int key) {
    this.key = key;
    height = 1;
  }

  public int getHeight() {
    int rightHeight = getNodeHeight(right);
    int leftHeight = getNodeHeight(left);

    return leftHeight > rightHeight ? leftHeight + 1 : rightHeight + 1;
  }

  public int getBalance() {
    int rightHeight = getNodeHeight(right);
    int leftHeight = getNodeHeight(left);

    return rightHeight - leftHeight;
  }

  public static int getNodeHeight(Node node) {
    return node == null ? 0 : node.getHeight();
  }
}
