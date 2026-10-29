import java.util.*;

public class Graph {
    private HashMap<String, Node> nodes;

    public Graph(){}

    public void addNode(Node n){
        nodes.put(n.getName(), n);
        if(nodes.containsKey(n)) System.out.println("il nodo è già presente nella lista");
    }

}
