package com.abra.algorithms.task_6.b_tree;

import java.util.LinkedList;
import java.util.List;

public class Btree {

  private int THRESHOLD = 1_000_000_000;

  private int factor;
  private Node root;

  public Btree(int factor) {
    this.factor = factor;
    root = new Node();
  }

  public boolean contains(int key) {
    if (root.getElements().isEmpty()) {
      return false;
    }

    return contains(key, root);
  }

  private boolean contains(int key, Node node) {
    List<Integer> elements = node.getElements();

    if (node.getChildren().isEmpty()) {
      return elements.contains(key);
    }

    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (element.equals(key)) {
        return true;
      }

      if (key < element) {
        return contains(key, node.getChildren().get(i));
      }
    }

    return contains(key, node.getChildren().get(elements.size()));
  }

  public void add(int key) {
    if (root.getChildren().isEmpty() && root.getElements().size() < 2 * factor - 1) {
      addElementToList(key, root.getElements());

      return;
    }

    if (root.getElements().size() >= 2 * factor - 1) {
      root = splitNode(root, null, -1);
    }

    root = add(key, root);
  }

  private Node add(int key, Node node) {

    int count = node.getElements().size();

    Node ref = node;
    for (int i = 0; i < count; i++) {
      Integer element = ref.getElements().get(i);
      if (key == element) {
        return node;
      }
      if (key < element) {

        if (ref.getChildren().isEmpty()) {
          addElementToList(key, ref.getElements());

          return node;
        } else {
          Node child = ref.getChildren().get(i);

          if (child.getElements().size() < 2 * factor - 1) {
            ref.getChildren().set(i, add(key, child));

            return ref;
          } else {
            ref = splitNode(child, ref, i);
            count++;
            i--;
          }
        }
      } else if (i == count - 1) {
        if (ref.getChildren().isEmpty()) {
          addElementToList(key, ref.getElements());

          return node;
        } else {
          Node child = ref.getChildren().get(i + 1);

          if (child.getElements().size() < 2 * factor - 1) {
            ref.getChildren().set(i + 1, add(key, child));

            return ref;
          } else {
            ref = splitNode(child, ref, i + 1);
            count++;
            i--;
          }
        }
      }
    }

    return ref;
  }

  private Node splitNode(Node child, Node parent, int index) {
    Integer middle = child.getElements().get(factor - 1);

    List<Integer> leftElements = new LinkedList<>(child.getElements().subList(0, factor - 1));
    List<Integer> rightElements = new LinkedList<>(child.getElements().subList(factor, 2 * factor - 1));

    List<Node> leftNodes = new LinkedList<>();
    List<Node> rightNodes = new LinkedList<>();

    if (!child.getChildren().isEmpty()) {
      leftNodes = new LinkedList<>(child.getChildren().subList(0, factor));
      rightNodes = new LinkedList<>(child.getChildren().subList(factor, 2 * factor));
    }

    Node left = new Node(leftElements, leftNodes);
    Node right = new Node(rightElements, rightNodes);

    if (parent == null) {
      List<Integer> elements = new LinkedList<>();
      elements.add(middle);

      List<Node> nodes = new LinkedList<>();
      nodes.add(left);
      nodes.add(right);

      return new Node(elements, nodes);
    }

    parent.getElements().add(index, middle);

    parent.getChildren().remove(index);
    parent.getChildren().add(index, left);
    parent.getChildren().add(index + 1, right);

    return parent;
  }

  public String getRootElements() {
    return root.getElements().toString();
  }

  private void addElementToList(int key, List<Integer> elements) {
    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (key < element) {
        elements.add(i, key);
        return;
      } else if (key == element) {
        return;
      }
    }

    elements.add(key);
  }
}
