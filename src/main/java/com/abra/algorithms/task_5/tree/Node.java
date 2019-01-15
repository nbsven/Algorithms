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
    return height;
  }

  public int getBalance() {
    int rightHeight = getNodeHeight(right);
    int leftHeight = getNodeHeight(left);

    return rightHeight - leftHeight;
  }

  public static int getNodeHeight(Node node) {
    return node == null ? 0 : node.getHeight();
  }

  public void fixHeight() {
    if (right == null && left == null) {
      height = 1;
      return;
    }

    if (right == null) {
      height = left.height;
      return;
    }

    if (left == null) {
      height = right.height;
      return;
    }

    height = Math.max(right.height, left.height) + 1;
  }

  public void setLeft(Node left) {
    this.left = left;
    fixHeight();
  }

  public void setRight(Node right) {
    this.right = right;
    fixHeight();
  }
}
