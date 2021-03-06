import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Optional;
import java.util.Vector;

public class NodeHandler<T extends Number> {

    // this vector stores all the nodes
    private Vector<Node<T>> nodes = new Vector<>();

    private Algorithm<T> algorithm = new Algorithm<>(this);

    Vector<Node<T>> getNodes() {
        return nodes;
    }

    Algorithm<T> getAlgorithm() {
        return algorithm;
    }

    void connect(String name1, String name2, T len) {
        Node<T> node1 = getNodeByName(name1);
        Node<T> node2 = getNodeByName(name2);
        if (node1 == null && node2 == null) {
            node1 = new Node<>(name1);
            node2 = new Node<>(name2);
            nodes.add(node1);
            nodes.add(node2);
            node1.connect_node(node2, len);
        } else if (node1 != null && node2 == null) {
            node2 = new Node<>(name2);
            nodes.add(node2);
            node1.connect_node(node2, len);
        } else if (node1 == null) {
            node1 = new Node<>(name1);
            nodes.add(node1);
            node1.connect_node(node2, len);
        } else {
            node1.connect_node(node2, len);
        }
    }

    Node<T> getNodeByName(String name) {
        for(Node<T> node : nodes) {
            if(node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder ret = new StringBuilder();
        for(Node<T> n : nodes) {
            ret.append(n.getName()).append("->");
            for (HashMap.Entry<Node<T>, T> entry : n.getEdges().entrySet()) {
                Node<T> key = entry.getKey();
                T value = entry.getValue();
                ret.append(" ").append(key.getName()).append(",").append(value).append(";");
            }
            ret.append("\n");
        }
        return ret.toString();
    }

    static Optional<Json> handlerFromFile(String input){
        input = input + ".json";
        try {
            return Optional.of(new Json(input));
        } catch(ClassCastException ex) {
            Interactive.printFailure("Wrong data type supplied");
            return Optional.empty();
        } catch(NumberFormatException ex) {
            Interactive.printFailure("Number too large");
            return Optional.empty();
        } catch(FileNotFoundException ex) {
            Interactive.printFailure("file not found");
            return Optional.empty();
        } catch (Exception ex) {
            Interactive.printFailure("Exception in json parser: " + ex.getMessage() + "; " + ex.toString());
            return Optional.empty();
        }
    }

}
