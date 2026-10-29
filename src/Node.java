import java.util.ArrayList;

public class Node {
    private final String name;
    private ArrayList<Adjacency> adjacencies;

    public Node(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void addArch(Node n, int weight){
        adjacencies.add(new Adjacency(n, weight));
    }

    public void removeAdj(Node n){
        for(Adjacency a: adjacencies){
            if(a.getNode() == n){
                adjacencies.remove(a);
                break;
            }
        }
    }
}
