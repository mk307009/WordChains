package pl.m4.kata.wordchains.wordchain;

public class Node {
    private String name;
    private Node parent;
    private boolean isVisited;

    public Node(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public boolean isVisited() {
        return isVisited;
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node)) return false;

        Node node = (Node) o;

        return getName().equals(node.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }
}
