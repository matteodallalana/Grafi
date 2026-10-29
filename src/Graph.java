import java.util.*;

public class Graph<T>  {
    private HashMap<T, Node<T>> nodes;

    public Graph(){}

    public void addNode(Node<T> n){
        if(!nodes.containsKey(n.getValue())){
        nodes.put(n.getValue(), n);
        }
        if(nodes.containsKey(n.getValue())) System.out.println("il nodo è già presente nella lista");
    }
}
