import java.util.HashMap;

public class Node<T> implements Comparable<T>{
    private final T value;
    private HashMap<Node<T>, Integer> adjacencies;

    public Node(T value){
        this.value = value;
    }

    public T getValue(){
        return this.value;
    }

    public void addAdj(Node<T> n, int weight){
        adjacencies.put(n, weight);
    }

    @Override
    public int compareTo(T o) {
        return 0;
    }

    @Override
    public String toString(){
        return String.format(
          "| Node: %3s | --%3s-- | Node: %3s |"
        );
    }
}
