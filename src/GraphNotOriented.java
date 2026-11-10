import java.util.HashSet;
import java.util.PriorityQueue;

public class GraphNotOriented<T> extends Graph<T> {

    @Override
    public void addEdge(T source, T dest, float weight) {
        Node<T> startNode = nodes.get(source);
        Node<T> destNode = nodes.get(dest);

        if (startNode != null && destNode != null) {
            startNode.addAdjacency(destNode, weight); // Usa addAdjacency
            destNode.addAdjacency(startNode, weight); // Usa addAdjacency
        } else {
            System.out.println("One or both nodes do not exist.");
        }
    }

    private class Arco {
        T da, a;
        float peso;
        Arco(T da, T a, float peso) {
            this.da = da; this.a = a; this.peso = peso;
        }
    }

    public GraphNotOriented<T> prim(T radix) {
        GraphNotOriented<T> mst = new GraphNotOriented<>();
        if (!nodes.containsKey(radix)) return mst;

        HashSet<T> visitati = new HashSet<>();
        PriorityQueue<Arco> codaArchi = new PriorityQueue<>((a, b) -> Float.compare(a.peso, b.peso));

        mst.addNode(radix);
        visitati.add(radix);

        for (var entry : nodes.get(radix).getAdjacencies().entrySet()) {
            codaArchi.add(new Arco(radix, entry.getKey().getValue(), entry.getValue()));
        }

        while (!codaArchi.isEmpty()) {
            Arco arcoMinimo = codaArchi.poll();

            if (visitati.contains(arcoMinimo.a)) continue;

            mst.addNode(arcoMinimo.a);
            mst.addEdge(arcoMinimo.da, arcoMinimo.a, arcoMinimo.peso);

            T nuovoNodo = arcoMinimo.a;
            visitati.add(nuovoNodo);

            for (var entry : nodes.get(nuovoNodo).getAdjacencies().entrySet()) {
                T vicino = entry.getKey().getValue();
                if (!visitati.contains(vicino)) {
                    codaArchi.add(new Arco(nuovoNodo, vicino, entry.getValue()));
                }
            }
        }
        return mst;
    }

    public GraphNotOriented<T> kruskal() {
        return new GraphNotOriented<>();
    }
}
