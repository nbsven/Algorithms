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
    int rightHeight = getNodeHeight(left);
    int leftHeight = getNodeHeight(right);

    return leftHeight > rightHeight ? leftHeight : rightHeight;
  }

  public int getBalance() {
    int rightHeight = getNodeHeight(left);
    int leftHeight = getNodeHeight(right);

    return rightHeight - leftHeight;
  }

  public static int getNodeHeight(Node node) {
    return node == null ? 0 : node.getHeight();
  }
}
