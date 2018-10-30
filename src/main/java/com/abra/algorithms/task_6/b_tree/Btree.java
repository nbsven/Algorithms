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
    root.fixRelationWithChildren();
  }

  private Node add(int key, Node node) {

    int count = node.getElements().size();

    Node ref = node;
    for (int i = 0; i < count; i++) {
      Integer element = ref.getElements().get(i);
      if (key == element) {
        node.fixRelationWithChildren();
        return node;
      }
      if (key < element) {

        if (ref.getChildren().isEmpty()) {
          addElementToList(key, ref.getElements());

          node.fixRelationWithChildren();
          return node;
        } else {
          Node child = ref.getChildren().get(i);

          if (child.getElements().size() < 2 * factor - 1) {
            Node add = add(key, child);
            ref.getChildren().set(i, add);

            ref.fixRelationWithChildren();
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

          node.fixRelationWithChildren();
          return node;
        } else {
          Node child = ref.getChildren().get(i + 1);

          if (child.getElements().size() < 2 * factor - 1) {
            Node add = add(key, child);
            ref.getChildren().set(i + 1, add);

            ref.fixRelationWithChildren();
            return ref;
          } else {
            ref = splitNode(child, ref, i + 1);
            count++;
            i--;
          }
        }
      }
    }

    ref.fixRelationWithChildren();
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
    left.fixRelationWithChildren();
    Node right = new Node(rightElements, rightNodes);
    right.fixRelationWithChildren();

    if (parent == null) {
      List<Integer> elements = new LinkedList<>();
      elements.add(middle);

      List<Node> nodes = new LinkedList<>();
      nodes.add(left);
      nodes.add(right);

      Node newParent = new Node(elements, nodes);
      newParent.fixRelationWithChildren();

      return newParent;
    }

    parent.getElements().add(index, middle);

    parent.getChildren().remove(index);
    parent.getChildren().add(index, left);
    parent.getChildren().add(index + 1, right);
    parent.fixRelationWithChildren();

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

  public void remove(Integer key) {
    if (root.isLeaf()) {
      root.getElements().remove(key);
      return;
    }

    if (root.getElements().size() == 1 && root.getElements().contains(key) && !root.isLeaf()) {
      Node left = root.getChildren().get(0);
      Node right = root.getChildren().get(1);

      root = combineNodes(left, key, right);
      if (root.isLeaf()) {
        root.getElements().remove(key);
      } else {
        remove(key, root);
      }

      return;
    }

    searchForRemoving(key, root, null);
  }

  private void searchForRemoving(Integer key, Node node, Node parent) {
    List<Integer> elements = node.getElements();

    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (element.equals(key)) {
        alterRemoving(parent, node, key);
        return;
      } else if (key < element) {
        searchForRemoving(key, node.getChildren().get(i), node);
        return;
      }
    }

    searchForRemoving(key, node.getChildren().get(node.getChildren().size() - 1), node);
  }

  private void removeFromLeaf(Integer key, Node leaf, Node parent) {
    int size = leaf.getElements().size();

    for (int i = 0; i < size; i++) {
      Integer element = leaf.getElements().get(i);

      if (key.equals(element)) {
        if (size > factor - 1) {
          leaf.getElements().remove(key);
          return;
        } else {
          leaf.getElements().remove(key);
          isNeighborExists(leaf, parent);
          return;
        }
      }
    }

  }

  @SuppressWarnings("Duplicates")
  private void isNeighborExists(Node child, Node parent) {
    List<Node> nodes = parent.getChildren();
    int index = nodes.indexOf(child);
    int k1Index = -1;
    int k2Index = -1;
    int insertIndex = -1;
    int neighborIndex = -1;
    Node neighbor = null;
    Node leftNeighbor = null;
    Node rightNeighbor = null;

    if (index > 0) {
      neighborIndex = index - 1;
      leftNeighbor = nodes.get(index - 1);

      int neighborSize = leftNeighbor.getElements().size();
      if (neighborSize > factor - 1) {
        k1Index = neighborSize - 1;
        k2Index = index - 1;
        neighbor = leftNeighbor;
        insertIndex = 0;
      }
    }

    if (index < nodes.size() - 1) {
      neighborIndex = index + 1;
      rightNeighbor = nodes.get(index + 1);

      int neighborSize = rightNeighbor.getElements().size();
      if (neighborSize > factor - 1) {
        k1Index = 0;
        k2Index = index;
        neighbor = rightNeighbor;
        insertIndex = child.getElements().size();
      }
    }

    if (neighbor != null) {
      Integer k1 = neighbor.getElements().remove(k1Index);
      Integer k2 = parent.getElements().remove(k2Index);
      parent.getElements().add(k2Index, k1);
      child.getElements().add(insertIndex, k2);
      return;
    }

    if (index > 0) {
      Integer middle = parent.getElements().remove(Math.min(index, neighborIndex));
      parent.getChildren().remove(index);
      parent.getChildren().remove(neighborIndex);

      Node combined = combineNodes(leftNeighbor, middle, child);
      parent.getChildren().add(Math.min(index, neighborIndex), combined);
    }

    if (index < nodes.size() - 1) {
      Integer middle = parent.getElements().remove(Math.min(index, neighborIndex));
      parent.getChildren().remove(index);
      parent.getChildren().remove(neighborIndex);

      Node combined = combineNodes(child, middle, rightNeighbor);
      parent.getChildren().add(Math.min(index, neighborIndex), combined);
    }

  }

  private Node combineNodes(Node left, int middle, Node right) {
    List<Node> leftChildren = left.getChildren();
    List<Node> rightChildren = right.getChildren();

    List<Integer> leftElements = left.getElements();
    List<Integer> rightElements = right.getElements();

    List<Integer> elements = new LinkedList<>(leftElements);
    elements.add(middle);
    elements.addAll(rightElements);

    LinkedList<Node> nodes = new LinkedList<>(leftChildren);
    nodes.addAll(rightChildren);

    return new Node(elements, nodes);
  }

  @SuppressWarnings("Duplicates")
  private void remove(int key, Node node) {
    List<Integer> elements = node.getElements();

    for (int i = 0; i < elements.size(); i++) {
      Integer element = elements.get(i);

      if (key == element) {
        Node left = node.getChildren().get(i);
        Node right = node.getChildren().get(i + 1);

        if (left.getElements().size() > factor - 1) {
          Integer innerKey = left.getElements().get(left.getElements().size() - 1);
          node.getElements().remove(i);
          node.getElements().add(i, innerKey);

          alterRemoving(node, left, innerKey);

          return;
        } else if (right.getElements().size() > factor - 1) {
          Integer innerKey = right.getElements().get(0);
          node.getElements().remove(i);
          node.getElements().add(i, innerKey);

          alterRemoving(node, right, innerKey);

          return;
        } else {
          Integer middle = node.getElements().remove(i);
          left = node.getChildren().remove(i);
          right = node.getChildren().remove(i + 1);

          Node combined = combineNodes(left, middle, right);
          node.getChildren().set(i, combined);

          alterRemoving(node, combined, key);
          return;
        }
      }
    }
  }

  private void alterRemoving(Node parent, Node checkingNode, Integer keyForRemoving) {
    if (checkingNode.isLeaf()) {
      removeFromLeaf(keyForRemoving, checkingNode, parent);
    } else {
      remove(keyForRemoving, checkingNode);
    }
  }
}
