import java.util.HashMap;

public class Node<T> {
    private final T value;
    private final HashMap<Node<T>, Float> adjacencies;

    public Node(T value) {
        this.value = value;
        this.adjacencies = new HashMap<>();
    }

    public T getValue() {
        return this.value;
    }

    public void addAdjacency(Node<T> node, float weight) {
        this.adjacencies.put(node, weight);
    }

    public HashMap<Node<T>, Float> getAdjacencies() {
        return this.adjacencies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.adjacencies.forEach((node, weight) -> {
            sb.append(this.value)
                    .append(" -> ")
                    .append(node.getValue()) // Usa getValue()
                    .append(" (weight: ")
                    .append(weight)
                    .append(")\n");
        });
        return sb.toString();
    }
}
