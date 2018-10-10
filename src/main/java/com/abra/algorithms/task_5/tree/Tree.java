package com.abra.algorithms.task_5.tree;

public class Tree {

  private Node root;

  private Node rightRotation(Node node) {
    Node A = node.getLeft().getLeft();
    Node B = node.getLeft().getRight();
    Node C = node.getRight();

    Node result = node.getLeft();
    result.setLeft(A);
    node.setLeft(B);
    node.setRight(C);
    result.setRight(node);

    return result;
  }

  private Node leftRotation(Node node) {
    Node A = node.getLeft();
    Node B = node.getRight().getLeft();
    Node C = node.getRight().getRight();

    Node result = node.getRight();
    result.setRight(C);
    node.setLeft(A);
    node.setRight(B);
    result.setLeft(node);

    return result;
  }

  private Node balance(Node node) {
    if (node.getBalance() == 2) {

      if (node.getRight().getBalance() < 0) {
        Node right = rightRotation(node.getRight());
        node.setRight(right);
      }

      return leftRotation(node);
    }

    if (node.getBalance() == -2) {

      if (node.getLeft().getBalance() > 0) {
        Node left = leftRotation(node.getLeft());
        node.setLeft(left);
      }

      return rightRotation(node);
    }

    return node;
  }

  public void insert(int value) {
    root = insert(root, value);
  }

  private Node insert(Node node, int value) {
    if (node == null) {
      return new Node(value);
    }

    int key = node.getKey();

    if (value < key) {
      Node left = node.getLeft();
      node.setLeft(insert(left, value));
    }

    if (value > key) {
      Node right = node.getRight();
      node.setRight(insert(right, value));
    }

    return balance(node);
  }

  public void remove(int value) {
    root = remove(root, value);
  }

  private Node remove(Node node, int value) {
    if (node == null) {
      return null;
    }

    int key = node.getKey();

    if (value > key) {
      node.setRight(remove(node.getRight(), value));
    } else if (value < key) {
      node.setLeft(remove(node.getLeft(), value));
    } else {
      Node left = node.getLeft();
      Node right = node.getRight();

      if (right == null) {
        return left;
      }

      Node min = findMinNode(right);
      min.setRight(removeMinNode(right));
      min.setLeft(left);

      return balance(min);
    }

    return balance(node);
  }

  private Node removeMinNode(Node node) {
    if (node.getLeft() == null) {
      return node.getRight();
    }

    node.setLeft(removeMinNode(node.getLeft()));

    return balance(node);
  }

  private Node findMinNode(Node node) {
    return node.getLeft() != null ? node.getLeft() : node;
  }

  public boolean contains(int value) {
    return contains(root, value);
  }

  private boolean contains(Node node, int value) {
    if (node == null) {
      return false;
    }

    int key = node.getKey();

    if (value == key) {
      return true;
    }

    if (value > key) {
      return contains(node.getRight(), value);
    }

    return contains(node.getLeft(), value);
  }

  public int getRootBalance() {
    return root == null ? 0 : root.getBalance();
  }
}
