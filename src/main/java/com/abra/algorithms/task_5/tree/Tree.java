package com.abra.algorithms.task_5.tree;

public class Tree {

  private Node root;

  private Node rightRotation(Node node){
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

  private Node leftRotation(Node node){
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
}
