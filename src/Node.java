import java.util.HashMap;

public class Node {
    private final String id;
    private int col;
    private int row;
    private final HashMap<Node, Float> adjacencies;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
        this.adjacencies = new HashMap<>();
        this.id = String.format("%2s%2s", row, col);
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    public String getId() {return this.id;}

    public void addAdjacency(Node node, float weight) {
        this.adjacencies.put(node, weight);
    }

    public HashMap<Node, Float> getAdjacencies() {
        return this.adjacencies;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.adjacencies.forEach((node, weight) -> {
            sb.append(this.id)
                    .append(" -> ")
                    .append(node.getId())
                    .append(" (weight: ")
                    .append(weight)
                    .append(")\n");
        });
        return sb.toString();
    }
}
