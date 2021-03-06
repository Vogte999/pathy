import java.util.HashMap;
import java.util.Optional;

class Node<T extends Number> {

    // each node has a name and all the nodes it's connected to
    private String name;
    private HashMap<Node<T>, T> edges = new HashMap<>();

    private Optional<T> distance = Optional.empty();

    private Node<T> predecessor;

    Node(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    Node<T> getPredecessor() {
        return predecessor;
    }


    Optional<T> getDistance() {
        return distance;
    }


    void setPredecessor(Node<T> predecessor) {
        this.predecessor = predecessor;
    }

    void setDistance(Optional<T> distance) {
        this.distance = distance;
    }

    HashMap<Node<T>, T> getEdges() {
        return edges;
    }

    // if you want to connect a specified node with a specified edge length...
    void connect_node(Node<T> node, T length) {
        // ...pushes the new node to the edges Hash...
        edges.put(node, length);
        //...and lets the other node know about the new connection.
        node.connect_node_back(this, length);
    }

    // connect_node method, but without calling it on the other node again, the other node already has the edge.
    private void connect_node_back(Node<T> node, T length) {
        edges.put(node, length);
    }

}
