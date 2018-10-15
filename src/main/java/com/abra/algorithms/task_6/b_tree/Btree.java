package com.abra.algorithms.task_6.b_tree;

import java.util.List;

public class Btree {

  private int THRESHOLD = 1_000_000_000;

  private int factor;
  private Node root;

  public boolean contains(int key) {
    if (root.getElements().isEmpty()) {
      return false;
    }

    return contains(key, root);
  }

  private boolean contains(int key, Node node) {
    List<Integer> elements = node.getElements();

    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (element.equals(key)) {
        return true;
      }

      if (key < element) {
        return contains(key, node.getChildren().get(i));
      }
    }

    return contains(key, node.getChildren().get(elements.size() + 1));
  }

  public void add(int key) {
    if (root.getChildren().isEmpty() && root.getElements().size() < 2 * factor - 2) {
      addElementToList(key, root.getElements());
    }

    add(key, root);
  }

  private int add(int key, Node node) {
    List<Integer> elements = node.getElements();

    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (key < element) {
        int temp = add(key, node.getChildren().get(i));

        if (temp == THRESHOLD) {
          return THRESHOLD;
        }

        if (elements.size() < 2 * factor - 2) {
          addElementToList(temp, elements);
        } else {
          addElementToList(temp,elements);


        }

      }
    }
  }

  private void addElementToList(int key, List<Integer> elements) {
    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (key <= element) {
        elements.add(i, key);
      }
    }
  }
}
