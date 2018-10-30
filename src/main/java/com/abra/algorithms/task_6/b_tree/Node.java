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

  private Node parent;
  private List<Integer> elements = new LinkedList<>();
  private List<Node> children = new LinkedList<>();

  public Node(List<Integer> elements, List<Node> children) {
    this.elements = elements;
    this.children = children;

  }

  public boolean isLeaf() {
    return children.isEmpty();
  }

  public boolean isRoot() {
    return parent == null;
  }

  public void fixRelationWithChildren(){
    children.forEach(n -> n.setParent(this));
  }
}
