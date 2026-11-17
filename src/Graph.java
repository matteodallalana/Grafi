import java.util.HashMap;

public abstract class Graph<T> {
    protected HashMap<String, Node> nodes;
    private String size;


    public Graph(String rrcc) {
        this.size = rrcc;
        this.nodes = new HashMap<>();
    }

    public void addNode(String id) {
        if (!nodes.containsKey(id)) {
            nodes.put(id, new Node(-1, -1));
        } else {
            System.out.println("Element already inside the graph.");
        }
    }

    public void addNode(int col, int row) {
        String id = "" + row + col;
        if (!nodes.containsKey(id)) {
            nodes.put(id, new Node(col, row));
        } else {
            System.out.println("Element already inside the graph.");
        }
    }

    public HashMap<String, Node> getNodesMap() {
        return this.nodes;
    }


    public void addEdge(T start, T dest, float weight) {
        Node startNode = nodes.get(start);
        Node destNode = nodes.get(dest);

        if (startNode != null && destNode != null) {
            startNode.addAdjacency(destNode, weight);
        } else {
            System.out.println("One or both nodes do not exist.");
        }
    }

    public int getCols(){
        return Integer.parseInt(this.size) % 100;
    }

    public int getRows(){
        return Integer.parseInt(this.size) / 100;
    }

    public String getSize(){
        return this.size;
    }



    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String key : nodes.keySet()) {
            sb.append("Nodo ").append(key).append(" -> ");
            Node node = nodes.get(key);

            for (var entry : node.getAdjacencies().entrySet()) {
                String vicino = entry.getKey().getId();
                float peso = entry.getValue();
                sb.append("[").append(vicino).append(" (peso: ").append(peso).append(")] ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }
}
